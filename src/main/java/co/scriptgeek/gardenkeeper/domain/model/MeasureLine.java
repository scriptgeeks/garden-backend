package co.scriptgeek.gardenkeeper.domain.model;

import org.joda.time.Instant;

/**
 * Created by xiaofeng on 15/4/7.
 */
public class MeasureLine {
    private Instant timestamp;
    private Float[] measureValues;

    public MeasureLine(Instant timestamp, Float[] measureValues) {
        this.timestamp = timestamp;
        this.measureValues = measureValues;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Float[] getMeasureValues() {
        return measureValues;
    }

    public void addMeasureValue(ChannelId channelId, float measureValue) {
        this.measureValues[channelId.getChannelIndex()] = measureValue;
    }

}
