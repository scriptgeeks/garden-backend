package co.scriptgeek.gardenkeeper.domain.model;

/**
 * Created by xiaofeng on 15/4/7.
 */
public class MeasureSubmittedEvent {
    private Measurement measurement;

    public void set(Measurement measurement) {
        this.measurement = measurement;
    }

    public Measurement getMeasurement() {
        return measurement;
    }
}
