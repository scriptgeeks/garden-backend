package co.scriptgeek.gardenkeeper.application.handler;

import co.scriptgeek.gardenkeeper.application.dto.MeasureCollectDTO;
import co.scriptgeek.gardenkeeper.application.event.MeasureCollectEvent;
import co.scriptgeek.gardenkeeper.domain.model.ChannelSet;
import co.scriptgeek.gardenkeeper.domain.model.MeasurePackage;
import co.scriptgeek.gardenkeeper.domain.repository.ChannelSetRepository;
import co.scriptgeek.gardenkeeper.domain.repository.MeasurePackageRepository;
import com.lmax.disruptor.EventHandler;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaofeng on 15/4/16.
 */
@Component
public class MeasureCollectEventHandler implements EventHandler<MeasureCollectEvent> {

    private ScheduledExecutorService scheduledExecutorService;

    private volatile MeasurePackage measurePackage;

    private volatile DateTime lastPackageTime = DateTime.now();

    @Autowired
    private MeasurePackageRepository measurePackageRepository;
    @Autowired
    private ChannelSetRepository channelSetRepository;

    @PostConstruct
    public void init() {
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            final MeasurePackage toSave = measurePackage;
            lastPackageTime = measurePackage.getPackageTime();
            measurePackage = null;

            measurePackageRepository.save(toSave);
        }, 60, 60, TimeUnit.SECONDS);
    }

    @Override
    public void onEvent(MeasureCollectEvent event, long sequence, boolean endOfBatch) throws Exception {
        final MeasureCollectDTO measureCollect = event.getMeasureCollect();
        final ChannelSet channelSet =
                channelSetRepository.getByTimestamp(new Instant(measureCollect.getTimestamp()));
        if (measurePackage == null) {
            measurePackage = new MeasurePackage(channelSet, lastPackageTime);
        }

        measureCollect.getMeasureValues().stream()
                .forEach(measureValueDTO ->
                        measurePackage.packing(
                                channelSet.getChannelIdByName(measureValueDTO.getChannelName()),
                                new Instant(measureCollect.getTimestamp()),
                                measureValueDTO.getMeasureValue()));
    }

}
