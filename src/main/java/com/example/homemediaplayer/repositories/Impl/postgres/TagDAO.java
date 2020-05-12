package com.example.homemediaplayer.repositories.Impl.postgres;

import com.example.homemediaplayer.entity.Tag;
import com.example.homemediaplayer.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagDAO implements TagRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Tag> rowMapper = (RowStr, RowNum) -> new Tag(
            RowStr.getLong("id"),
            RowStr.getString("value")
    );

    @Override
    public List<Tag> get() {
        String GET_ALL = "Select * from player.tag";
        return jdbcTemplate.query(GET_ALL, rowMapper);
    }

    @Override
    public Tag get(Long id) {
        try {
            String GET_BY_ID = "Select * from player.tag where id = ?";
            return jdbcTemplate.queryForObject(GET_BY_ID, rowMapper, id);
        }
        catch (EmptyResultDataAccessException exc){
            return null;
        }
    }

    @Override
    public Tag get(String value) {
        try {
            String GET_BY_VALUE = "Select * from player.tag where value = ?;";
            return jdbcTemplate.queryForObject(GET_BY_VALUE, rowMapper, value);
        }
        catch (EmptyResultDataAccessException exc){
            return null;
        }
    }

    @Override
    public List<Tag> get(String... tags) {
        StringBuilder query = new StringBuilder();
        String GET_BY_VALUES = "Select * from player.tag where value = ?";
        for (int i = 0; i < tags.length - 1; i++){
            query
                    .append(GET_BY_VALUES)
                    .append(" UNION ");
        }
        query
                .append(GET_BY_VALUES)
                .append(";");
        return jdbcTemplate.query(query.toString(), rowMapper, (Object[]) tags);
    }

    @Override
    public void insertTag(String... values) {
        String INSERT = "insert into player.tag(value) Values";
        String INSERT_ON_CONFLICT = "on conflict do nothing;";
        String query = INSERT + "(?),".repeat(Math.max(0, values.length - 1)) +
                "(?) " +
                INSERT_ON_CONFLICT;
        jdbcTemplate.update(query, (Object[]) values);
    }
}
