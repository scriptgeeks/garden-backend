package co.scriptgeek.gardenkeeper.domain.model;

/**
 * Created by xiaofeng on 15/4/7.
 */
public class MeasureValue {
    private String channelName;
    private float measureValue;

    public MeasureValue(String channelName, float measureValue) {
        this.channelName = channelName;
        this.measureValue = measureValue;
    }

    public String getChannelName() {
        return channelName;
    }

    public float getMeasureValue() {
        return measureValue;
    }
}
