package co.scriptgeek.gardenkeeper.web;

import co.scriptgeek.gardenkeeper.web.dto.ChannelDTO;
import co.scriptgeek.gardenkeeper.web.dto.DataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiaofeng on 15/4/7.
 */
@RestController
public class ChannelController {

    @Autowired
    private CassandraOperations cassandraOperations;

    @RequestMapping(value = "/channels", method = RequestMethod.POST)
    public void addChannel(@RequestBody ChannelDTO channelDTO) {

    }

    @RequestMapping(value = "/channel/{channelId}", method = RequestMethod.GET)
    public ChannelDTO getChannel(int channelId) {
         return null;
    }

    @RequestMapping(value = "/channel/{channelId}", method = RequestMethod.DELETE)
    public void deleteChannel(int channelId) {

    }

    @RequestMapping(value = "/channel/{channelId}", method = RequestMethod.PUT)
    public void updateChannel(@RequestBody ChannelDTO channelDTO) {
       // cassandraOperations.execute();
    }
}
