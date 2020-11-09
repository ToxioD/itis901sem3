package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Roll;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class RollHistRepositoryImpl implements RollHistRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_USER_ID = "select * from rolls where userId = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from rolls";

    //language=SQL
    private static final String SQL_INSERT = "insert into rolls(userId, dices, result, color) " +
            "values (?, ?, ?, ?)";

    //language=SQL
    private static final String SQL_DELETE_LAST_BY_USER_ID = "delete from rolls where userId = ? and (id) in " +
            "(select min(id) from rolls where userId = ?);";


    private JdbcTemplate jdbcTemplate;

    public RollHistRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Roll> rollRowMapper = (row, rowNumber) -> Roll.builder()
            .id(row.getLong("id"))
            .userId(row.getLong("userId"))
            .dices(row.getString("dices"))
            .result(row.getLong("result"))
            .color(row.getString("color"))
            .build();

    @Override
    public List<Roll> findAllByUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_BY_USER_ID, rollRowMapper, userId);
    }

    @Override
    public void save(Roll entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getUserId(),
                entity.getDices(),
                entity.getResult(),
                entity.getColor());
    }

    @Override
    public void update(Roll entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Roll> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Roll> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, rollRowMapper);
    }

    @Override
    public void deleteLastByUserId(Long userId) {
        jdbcTemplate.update(SQL_DELETE_LAST_BY_USER_ID, userId, userId);
    }
}
