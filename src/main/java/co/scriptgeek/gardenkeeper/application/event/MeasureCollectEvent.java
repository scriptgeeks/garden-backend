package co.scriptgeek.gardenkeeper.application.event;

import co.scriptgeek.gardenkeeper.application.dto.MeasureCollectDTO;

/**
 * Created by xiaofeng on 15/4/7.
 */
public class MeasureCollectEvent {
    private MeasureCollectDTO measureCollect;

    public void set(MeasureCollectDTO measureCollect) {
        this.measureCollect = measureCollect;
    }

    public MeasureCollectDTO getMeasureCollect() {
        return measureCollect;
    }
}
