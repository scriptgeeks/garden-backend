package co.scriptgeek.gardenkeeper.web;

import co.scriptgeek.gardenkeeper.application.dto.MeasureCollectDTO;
import co.scriptgeek.gardenkeeper.application.dto.MeasureValueDTO;
import co.scriptgeek.gardenkeeper.application.service.MeasureCollectService;
import co.scriptgeek.gardenkeeper.domain.model.Measurement;
import co.scriptgeek.gardenkeeper.domain.model.MeasureValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by xiaofeng on 15/4/7.
 */
@RestController
public class MeasureValueController {

    @Autowired
    private MeasureCollectService measureCollectService;

    @RequestMapping(value = "/measure_values", method = RequestMethod.POST)
    public void collectMeasureValues(@RequestBody MeasureCollectDTO measureValues) {
        final Measurement measurement = new Measurement(
                measureValues.getTimestamp() > 0 ? new Date(measureValues.getTimestamp()) : new Date(),
                buildValues(measureValues.getMeasureValues())
        );
        measureCollectService.submitMeasureCollect(measurement);
    }

    private MeasureValue[] buildValues(MeasureValueDTO[] measureValueDTOs) {
        final MeasureValue[] measureValues = new MeasureValue[measureValueDTOs.length];
        for (int index = 0; index < measureValueDTOs.length; index++) {
            measureValues[index] = new MeasureValue(
                    measureValueDTOs[index].getChannelName(),
                    measureValueDTOs[index].getMeasureValue());
        }

        return measureValues;
    }
}
