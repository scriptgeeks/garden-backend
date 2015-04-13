package co.scriptgeek.gardenkeeper.application.dto;

import java.util.Arrays;

/**
 * Created by xiaofeng on 15/4/7.
 */
public class MeasureCollectDTO {

    private long timestamp;
    private MeasureValueDTO[] measureValues;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public MeasureValueDTO[] getMeasureValues() {
        return measureValues;
    }

    public void setMeasureValues(MeasureValueDTO[] measureValues) {
        this.measureValues = measureValues;
    }

    @Override
    public String toString() {
        return "MeasureValuesDTO{" +
                "timestamp=" + timestamp +
                ", measureValues=" + Arrays.toString(measureValues) +
                '}';
    }
}
