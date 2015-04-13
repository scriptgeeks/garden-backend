package co.scriptgeek.gardenkeeper.domain.model;

import java.util.Date;
import java.util.Map;

/**
 * Created by xiaofeng on 15/4/8.
 */
public class ChannelSet {
    private ChannelSetId channelSetId;
    private String tableName;
    private Date validFrom;
    private Date validTo;
    private Map<Integer, MeasureChannel> channels;


}
