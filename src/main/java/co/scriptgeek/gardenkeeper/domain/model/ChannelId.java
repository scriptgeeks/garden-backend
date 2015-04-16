package co.scriptgeek.gardenkeeper.domain.model;

/**
 * Created by xiaofeng on 15/4/16.
 */
public class ChannelId {
    private ChannelSetId channelSetId;
    private int channelIndex;

    public ChannelId(ChannelSetId channelSetId, int channelIndex) {
        this.channelSetId = channelSetId;
        this.channelIndex = channelIndex;
    }

    public ChannelSetId getChannelSetId() {
        return channelSetId;
    }

    public int getChannelIndex() {
        return channelIndex;
    }
}
