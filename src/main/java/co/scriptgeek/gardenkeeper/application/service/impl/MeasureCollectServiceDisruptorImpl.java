package co.scriptgeek.gardenkeeper.application.service.impl;

import co.scriptgeek.gardenkeeper.application.service.MeasureCollectService;
import co.scriptgeek.gardenkeeper.domain.model.Measurement;
import co.scriptgeek.gardenkeeper.domain.model.MeasurePackage;
import co.scriptgeek.gardenkeeper.domain.model.MeasurePackageRepository;
import co.scriptgeek.gardenkeeper.domain.model.MeasureSubmittedEvent;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by xiaofeng on 15/4/7.
 */
@Service
public class MeasureCollectServiceDisruptorImpl implements MeasureCollectService {
    private RingBuffer<MeasureSubmittedEvent> ringBuffer;
    private Disruptor<MeasureSubmittedEvent> disruptor;
    private SequenceBarrier sequenceBarrier;

    @Autowired
    private MeasurePackageRepository measurePackageRepository;


    @PostConstruct
    public void init() {
        final Executor executor = Executors.newSingleThreadExecutor();
        disruptor = new Disruptor<>(MeasureSubmittedEvent::new, 1024 * 1024, executor,
                ProducerType.MULTI, new SleepingWaitStrategy());
        disruptor.handleEventsWith(new BatchMeasureSubmittedEventHandler());
        ringBuffer = disruptor.start();
    }

    @PreDestroy
    public void destroy() {
        disruptor.halt();
    }

    @Override
    public void submitMeasureCollect(Measurement measurement) {
        final long sequenceToPublish = ringBuffer.next();
        try {
            final MeasureSubmittedEvent measureEvent = ringBuffer.get(sequenceToPublish);
            measureEvent.set(measurement);
        }
        finally {
            ringBuffer.publish(sequenceToPublish);
        }
    }

    private class BatchMeasureSubmittedEventHandler implements EventHandler<MeasureSubmittedEvent> {

        private ScheduledExecutorService scheduledExecutorService;

        private MeasurePackage measurePackage;

        final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

        public BatchMeasureSubmittedEventHandler() {
            scheduledExecutorService = Executors.newScheduledThreadPool(1);
            scheduledExecutorService.scheduleAtFixedRate(
                    this::packMeasureCollectList, 60, 60, TimeUnit.SECONDS);
        }

        @Override
        public void onEvent(MeasureSubmittedEvent event, long sequence, boolean endOfBatch) throws Exception {
            final Measurement measurement = event.getMeasurement();
            rwl.writeLock().lock();
            try {
                if (measurePackage == null) {
                    measurePackage = new MeasurePackage(measurement.getTimestamp(), new ArrayList<>());
                }
                measurePackage.add(measurement);
                if (measurePackage.barrierReached()) {
                    measurePackage.markPackaged();
                    measurePackageRepository.save(measurePackage);
                    measurePackage = null;
                }
            }
            finally {
                rwl.writeLock().unlock();
            }
        }

        private void packMeasureCollectList() {
            rwl.readLock().lock();
            try {

                measurePackage = null;
            }
            finally {
                rwl.readLock().unlock();
            }
        }
    }
}
