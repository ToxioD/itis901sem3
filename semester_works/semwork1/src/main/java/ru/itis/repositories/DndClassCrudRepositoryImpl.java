package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.DndClass;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class DndClassCrudRepositoryImpl implements CrudRepository<DndClass> {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from classes";

    private JdbcTemplate jdbcTemplate;

    public DndClassCrudRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<DndClass> classRowMapper = (row, rowNumber) -> DndClass.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .description(row.getString("description"))
            .hitDice(row.getInt("hitDice"))
            .isSpellcasting(row.getBoolean("isSpellcasting"))
            .build();

    @Override
    public void save(DndClass entity) {

    }

    @Override
    public void update(DndClass entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<DndClass> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<DndClass> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, classRowMapper);
    }

    @Override
    public List<DndClass> findAllByIds(List<Long> ids) {
        return null;
    }
}
