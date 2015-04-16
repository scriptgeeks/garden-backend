package co.scriptgeek.gardenkeeper.application.dto;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaofeng on 15/4/7.
 */
public class MeasureCollectDTO {

    private long timestamp;
    private List<MeasureValueDTO> measureValues;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<MeasureValueDTO> getMeasureValues() {
        return measureValues;
    }

    public void setMeasureValues(List<MeasureValueDTO> measureValues) {
        this.measureValues = measureValues;
    }

}
