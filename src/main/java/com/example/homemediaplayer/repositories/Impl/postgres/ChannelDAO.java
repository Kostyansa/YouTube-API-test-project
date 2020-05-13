package com.example.homemediaplayer.repositories.Impl.postgres;

import com.example.homemediaplayer.entity.ChannelDTO;
import com.example.homemediaplayer.repositories.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChannelDAO implements ChannelRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<ChannelDTO> rowMapper = (RowStr, RowNum) -> new ChannelDTO(
            RowStr.getLong("id"),
            RowStr.getString("name"),
            RowStr.getString("description"),
            RowStr.getString("eTag"),
            RowStr.getString("youtubeId"),
            RowStr.getString("uploadedId"),
            RowStr.getString("uploadedETag")
    );

    @Override
    public List<ChannelDTO> getChannels() {
        String GET_BY_ID = "select * from player.channel";
        return jdbcTemplate.query(GET_BY_ID, rowMapper);
    }

    @Override
    public ChannelDTO getChannel(Long id) {
        try {
            String GET_BY_ID = "select * from player.channel where id = ?";
            return jdbcTemplate.queryForObject(GET_BY_ID, rowMapper, id);
        }
        catch (EmptyResultDataAccessException exc){
            return null;
        }
    }

    @Override
    public ChannelDTO getChannel(String youtubeId) {
        try {
            String GET_BY_ID = "select * from player.channel where youtubeId = ?";
            return jdbcTemplate.queryForObject(GET_BY_ID, rowMapper, youtubeId);
        }
        catch (EmptyResultDataAccessException exc){
            return null;
        }
    }

    @Override
    public void updateChannel(ChannelDTO channelDTO) {
        String UPDATE = "update player.channel set name = ?, description = ? " +
                "eTag = ?, youtubeId = ?, " +
                "uploadedId = ?, uploadedETag = ? " +
                "where id = ?;";
        jdbcTemplate.update(UPDATE,
                channelDTO.getName(),
                channelDTO.getDescription(),
                channelDTO.getETag(),
                channelDTO.getYoutubeId(),
                channelDTO.getUploadedId(),
                channelDTO.getUploadedETag(),
                channelDTO.getId());
    }

    @Override
    public int createChannel(ChannelDTO channelDTO) {
        String INSERT = "insert into player.channel(name, description, eTag, youtubeId, uploadedId, uploadedETag) " +
                "Values(?, ?, ?, ?, ?, ?) on conflict (youtubeId) do nothing";
        return jdbcTemplate.update(INSERT,
                channelDTO.getName(),
                channelDTO.getDescription(),
                channelDTO.getETag(),
                channelDTO.getYoutubeId(),
                channelDTO.getUploadedId(),
                channelDTO.getUploadedETag());
    }

    @Override
    public void deleteChannel(Long id) {
        String DELETE = "delete from player.channel where id = ?";
        jdbcTemplate.update(DELETE, id);
    }
}
