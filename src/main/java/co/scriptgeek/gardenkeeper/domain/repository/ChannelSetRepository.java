package co.scriptgeek.gardenkeeper.domain.repository;

import co.scriptgeek.gardenkeeper.domain.model.ChannelSet;
import org.joda.time.Instant;

/**
 * Created by xiaofeng on 15/4/16.
 */
public interface ChannelSetRepository {

    ChannelSet getByTimestamp(Instant timestamp);
}
