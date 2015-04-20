package co.scriptgeek.gardenkeeper.persistence.repoimpl;

import co.scriptgeek.gardenkeeper.domain.model.Channel;
import co.scriptgeek.gardenkeeper.domain.model.ChannelSet;
import co.scriptgeek.gardenkeeper.domain.model.ChannelSetId;
import co.scriptgeek.gardenkeeper.domain.repository.ChannelSetRepository;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaofeng on 15/4/19.
 */
@Repository
public class ChannelSetRepositoryImpl implements ChannelSetRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ChannelSet getByTimestamp(Instant timestamp) {


        return jdbcTemplate.query("SELECT * FROM gk_channelset WHERE ? >= valid_from AND ? <= valid_to", rs -> {
            ObjectMapper mapper = new ObjectMapper();

            return new ChannelSet(
                    new ChannelSetId(rs.getLong("chnsetid")),
                    rs.getString("tablename"),
                    rs.getDate("valid_from"),
                    rs.getDate("valid_to"),
                 null//   mapper.readValue("config", ArrayList<Channel>.class)
            );
        }, timestamp.toDate(), timestamp.toDate());
    }
}
