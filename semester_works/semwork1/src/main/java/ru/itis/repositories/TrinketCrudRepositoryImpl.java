package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Trinket;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TrinketCrudRepositoryImpl implements CrudRepository<Trinket> {

    //language=SQL
    private static final String SQL_SELECT_ALL_BY_IDS = "select * from trinkets where id in (";

    private JdbcTemplate jdbcTemplate;

    public TrinketCrudRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Trinket> trinketRowMapper = (row, rowNumber) -> Trinket.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .build();

    @Override
    public void save(Trinket entity) {

    }

    @Override
    public void update(Trinket entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Trinket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Trinket> findAll() {
        return null;
    }

    @Override
    public List<Trinket> findAllByIds(List<Long> ids) {
        String query = SQL_SELECT_ALL_BY_IDS + String.join(",", Collections.nCopies(ids.size(), "?")) + ")";
        return jdbcTemplate.query(query, ids.toArray(), trinketRowMapper);
    }
}
