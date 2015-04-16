package co.scriptgeek.gardenkeeper.web;

import co.scriptgeek.gardenkeeper.application.dto.MeasureCollectDTO;
import co.scriptgeek.gardenkeeper.application.service.MeasureCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiaofeng on 15/4/7.
 */
@RestController
public class MeasureValueController {

    @Autowired
    private MeasureCollectService measureCollectService;

    @RequestMapping(value = "/measure_values", method = RequestMethod.POST)
    public void collectMeasureValues(@RequestBody MeasureCollectDTO measureCollect) {
        measureCollectService.submitMeasureCollect(measureCollect);
    }
}
