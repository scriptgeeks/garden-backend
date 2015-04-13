package co.scriptgeek.gardenkeeper.application.dto;

/**
 * Created by xiaofeng on 15/4/7.
 */
public class MeasureValueDTO {
    private String channelName;
    private float measureValue;

    public float getMeasureValue() {
        return measureValue;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void setMeasureValue(float measureValue) {
        this.measureValue = measureValue;
    }

    @Override
    public String toString() {
        return "MeasureValue{" +
                "channelName='" + channelName + '\'' +
                ", measureValue='" + measureValue + '\'' +
                '}';
    }
}
