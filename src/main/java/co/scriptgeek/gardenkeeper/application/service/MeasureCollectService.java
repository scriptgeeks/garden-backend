package co.scriptgeek.gardenkeeper.application.service;

import co.scriptgeek.gardenkeeper.application.dto.MeasureCollectDTO;

/**
 * Created by xiaofeng on 15/4/7.
 */
public interface MeasureCollectService {

    void submitMeasureCollect(MeasureCollectDTO measureValueList);
}
