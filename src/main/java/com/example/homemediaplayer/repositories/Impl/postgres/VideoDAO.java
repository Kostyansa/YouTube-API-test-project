package com.example.homemediaplayer.repositories.Impl.postgres;

import com.example.homemediaplayer.entity.Tag;
import com.example.homemediaplayer.entity.VideoDTO;
import com.example.homemediaplayer.repositories.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

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
                "select video_id from video_has_tag where tag_id in (");
        for (Tag tag : tags){
            query
                    .append(tag.getId())
            .append(",");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(") as tags_ids) as video_ids ")
                .append("order by (likes - 2*dislikes)/(views*(1 + (duration - ?)^2))")
                .append("limit ?;");
        return jdbcTemplate.query(query.toString(), rowMapper, limit);
    }

    @Override
    public List<VideoDTO> getControversialVideosByTagsAndDuration(List<Tag> tags, Duration duration, Long limit) {

        StringBuilder query = new StringBuilder("select " +
                "id, name, description, date, duration, " +
                "likes, dislikes, views, " +
                "eTag, youtubeId, channel_id " +
                "from player.video where id in (" +
                "select video_id from video_has_tag where tag_id in (");
        for (Tag tag : tags){
            query
                    .append(tag.getId())
                    .append(",");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(") as tags_ids) as video_ids ")
                .append("order by 1/(views*(1 + (duration - ?)^2)*(1 + (likes - 2*dislikes)^2))")
                .append("limit ?;");
        return jdbcTemplate.query(query.toString(), rowMapper, limit);
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
                "channelId) " +
                "Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(INSERT,
                videoDTO.getName(),
                videoDTO.getDescription(),
                Timestamp.valueOf(videoDTO.getDate()),
                videoDTO.getDuration().toSeconds(),
                videoDTO.getLikes(),
                videoDTO.getDislikes(),
                videoDTO.getViews(),
                videoDTO.getETag(),
                videoDTO.getYoutubeId());
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
                "channelId = ?," +
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
