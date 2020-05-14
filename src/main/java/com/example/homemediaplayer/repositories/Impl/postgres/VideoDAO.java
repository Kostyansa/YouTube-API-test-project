package com.example.homemediaplayer.repositories.Impl.postgres;

import com.example.homemediaplayer.entity.Tag;
import com.example.homemediaplayer.entity.VideoDTO;
import com.example.homemediaplayer.repositories.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class VideoDAO implements VideoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<VideoDTO> rowMapper = (RowStr, RowNum) -> new VideoDTO(
            RowStr.getLong("id"),
            RowStr.getString("name"),
            RowStr.getString("description"),
            RowStr.getTimestamp("date").toLocalDateTime(),
            Duration.ofSeconds(RowStr.getLong("duration")),
            RowStr.getLong("likes"),
            RowStr.getLong("dislikes"),
            RowStr.getLong("views"),
            RowStr.getString("eTag"),
            RowStr.getString("youtubeId"),
            RowStr.getLong("channel_id")
    );

    @Override
    public List<VideoDTO> getBestVideosByTagsAndDuration(List<Tag> tags, Duration duration, Long limit) {
        StringBuilder query = new StringBuilder("select " +
                "id, name, description, date, duration, " +
                "likes, dislikes, views, " +
                "eTag, youtubeId, channel_id " +
                "from player.video where id in (" +
                "select video_id from player.video_has_tag where tag_id in (");
        for (Tag tag : tags){
            query
                    .append(tag.getId())
            .append(",");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(")) ")
                .append("order by (likes - 2*dislikes)/(views*(1 + (duration - ?)^2)) desc ")
                .append("limit ?;");
        return jdbcTemplate.query(query.toString(), rowMapper, duration.toSeconds(), limit);
    }

    @Override
    public List<VideoDTO> getControversialVideosByTagsAndDuration(List<Tag> tags, Duration duration, Long limit) {

        StringBuilder query = new StringBuilder("select " +
                "id, name, description, date, duration, " +
                "likes, dislikes, views, " +
                "eTag, youtubeId, channel_id " +
                "from player.video where id in (" +
                "select video_id from player.video_has_tag where tag_id in (");
        for (Tag tag : tags){
            query
                    .append(tag.getId())
                    .append(",");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(")) ")
                .append("order by 1/(views*(1 + (duration - ?)^2)*(1 + (likes - 2*dislikes)^2)) desc ")
                .append("limit ?;");
        return jdbcTemplate.query(query.toString(), rowMapper, duration.toSeconds(), limit);
    }

    @Override
    public VideoDTO getVideoByYoutubeId(String youtubeId) {
        try {
            String GET_BY_ID = "select * from player.video where youtubeId = ?";
            return jdbcTemplate.queryForObject(GET_BY_ID, rowMapper, youtubeId);
        }
        catch (EmptyResultDataAccessException exc){
            return null;
        }
    }

    @Override
    public void createLinksVideoHasTag(VideoDTO videoDTO, List<Tag> tags) {
        String INSERT_VIDEO_HAS_TAGS =
                "insert into player.video_has_tag(video_id, tag_id) values "
                        + ("(" + videoDTO.getId() + ", ?),").repeat(tags.size() - 1)
                        + "(" + videoDTO.getId() + ", ?) ";

        jdbcTemplate.update(INSERT_VIDEO_HAS_TAGS, tags.stream().map(Tag::getId).toArray());
    }

    @Override
    public void createVideo(VideoDTO videoDTO) {
        String INSERT = "insert into player.video(" +
                "name, " +
                "description, " +
                "date, " +
                "duration, " +
                "likes, " +
                "dislikes, " +
                "views, " +
                "eTag, " +
                "youtubeId, " +
                "channel_Id) " +
                "Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                "on conflict (youtubeId) do update set " +
                "name = EXCLUDED.name, " +
                "description = EXCLUDED.description, " +
                "date = EXCLUDED.date, " +
                "duration = EXCLUDED.duration, " +
                "likes = EXCLUDED.likes, " +
                "dislikes = EXCLUDED.dislikes, " +
                "views = EXCLUDED.views, " +
                "eTag = EXCLUDED.eTag, " +
                "youtubeId = EXCLUDED.youtubeId," +
                "channel_Id = EXCLUDED.channel_Id ";
        jdbcTemplate.update(INSERT,
                videoDTO.getName(),
                videoDTO.getDescription(),
                Timestamp.valueOf(videoDTO.getDate()),
                videoDTO.getDuration().toSeconds(),
                videoDTO.getLikes(),
                videoDTO.getDislikes(),
                videoDTO.getViews(),
                videoDTO.getETag(),
                videoDTO.getYoutubeId(),
                videoDTO.getChannelId());
    }

    @Override
    public void updateVideo(VideoDTO videoDTO) {
        String UPDATE = "update player.video set" +
                "name = ?, " +
                "description = ?, " +
                "date = ?, " +
                "duration = ?, " +
                "likes = ?, " +
                "dislikes = ?, " +
                "views = ?, " +
                "eTag = ?, " +
                "youtubeId = ?, " +
                "where id = ?;";
        jdbcTemplate.update(UPDATE,
                videoDTO.getName(),
                videoDTO.getDescription(),
                Timestamp.valueOf(videoDTO.getDate()),
                videoDTO.getDuration().toSeconds(),
                videoDTO.getLikes(),
                videoDTO.getDislikes(),
                videoDTO.getViews(),
                videoDTO.getETag(),
                videoDTO.getYoutubeId(),
                videoDTO.getId());
    }
}
