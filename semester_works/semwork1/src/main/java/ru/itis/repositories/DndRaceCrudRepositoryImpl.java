package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.DndRace;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class DndRaceCrudRepositoryImpl implements CrudRepository<DndRace> {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from races";

    private JdbcTemplate jdbcTemplate;

    public DndRaceCrudRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<DndRace> raceRowMapper = (row, rowNumber) -> DndRace.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .description(row.getString("description"))
            .ability(row.getString("ability"))
            .size(row.getString("size"))
            .speed(row.getInt("speed"))
            .hasDarkvision(row.getBoolean("hasDarkvision"))
            .build();

    @Override
    public void save(DndRace entity) {

    }

    @Override
    public void update(DndRace entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<DndRace> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<DndRace> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, raceRowMapper);
    }

    @Override
    public List<DndRace> findAllByIds(List<Long> ids) {
        return null;
    }
}
