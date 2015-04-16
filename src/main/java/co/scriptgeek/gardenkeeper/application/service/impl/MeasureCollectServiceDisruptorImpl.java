package co.scriptgeek.gardenkeeper.application.service.impl;

import co.scriptgeek.gardenkeeper.application.dto.MeasureCollectDTO;
import co.scriptgeek.gardenkeeper.application.event.MeasureCollectEvent;
import co.scriptgeek.gardenkeeper.application.handler.MeasureCollectEventHandler;
import co.scriptgeek.gardenkeeper.application.service.MeasureCollectService;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by xiaofeng on 15/4/7.
 */
@Service
public class MeasureCollectServiceDisruptorImpl implements MeasureCollectService {
    private RingBuffer<MeasureCollectEvent> ringBuffer;
    private Disruptor<MeasureCollectEvent> disruptor;

    @Autowired
    private MeasureCollectEventHandler measureCollectEventHandler;

    @PostConstruct
    public void init() {
        final Executor executor = Executors.newSingleThreadExecutor();
        disruptor = new Disruptor<>(MeasureCollectEvent::new, 1024 * 1024, executor,
                ProducerType.MULTI, new SleepingWaitStrategy());
        disruptor.handleEventsWith(measureCollectEventHandler);
        ringBuffer = disruptor.start();
    }

    @PreDestroy
    public void destroy() {
        disruptor.halt();
    }

    @Override
    public void submitMeasureCollect(MeasureCollectDTO measureCollect) {
        final long sequenceToPublish = ringBuffer.next();
        try {
            final MeasureCollectEvent measureEvent = ringBuffer.get(sequenceToPublish);
            measureEvent.set(measureCollect);
        }
        finally {
            ringBuffer.publish(sequenceToPublish);
        }
    }

}
