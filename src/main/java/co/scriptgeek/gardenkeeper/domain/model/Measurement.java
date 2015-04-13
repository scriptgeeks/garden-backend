package co.scriptgeek.gardenkeeper.domain.model;

import java.util.Date;

/**
 * Created by xiaofeng on 15/4/7.
 */
public class Measurement {
    private Date timestamp;
    private MeasureValue[] measureValues;

    public Measurement(Date timestamp, MeasureValue[] measureValues) {
        this.timestamp = timestamp;
        this.measureValues = measureValues;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public MeasureValue[] getMeasureValues() {
        return measureValues;
    }
}
