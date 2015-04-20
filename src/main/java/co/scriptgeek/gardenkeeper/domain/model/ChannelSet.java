package co.scriptgeek.gardenkeeper.domain.model;


import java.util.Date;
import java.util.List;

/**
 * Created by xiaofeng on 15/4/8.
 */
public class ChannelSet {
    private ChannelSetId channelSetId;
    private String tableName;
    private Date validFrom;
    private Date validTo;
    private List<Channel> channels;

    public ChannelSet(ChannelSetId channelSetId, String tableName, Date validFrom, Date validTo, List<Channel> channels) {
        this.channelSetId = channelSetId;
        this.tableName = tableName;
        this.validFrom = validFrom;
        this.validTo = validTo;

        this.channels = channels;
    }

    public int getChannelCount() {
        return channels.size();
    }

    public ChannelSetId getChannelSetId() {
        return channelSetId;
    }

    public ChannelId getChannelIdByName(String channelName) {
        return new ChannelId(channelSetId,
                channels.stream()
                        .filter(channel -> channel.getChannelName().equals(channelName))
                        .findFirst()
                        .get()
                        .getChannelIndex());
    }
}
