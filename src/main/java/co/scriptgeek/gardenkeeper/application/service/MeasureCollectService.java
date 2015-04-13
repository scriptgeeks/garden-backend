package co.scriptgeek.gardenkeeper.application.service;

import co.scriptgeek.gardenkeeper.domain.model.Measurement;

/**
 * Created by xiaofeng on 15/4/7.
 */
public interface MeasureCollectService {

    void submitMeasureCollect(Measurement measurement);
}
