package ru.itis.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Roll;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "select * from users where email = ?";

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from users";

    //language=SQL
    private static final String SQL_INSERT = "insert into users(email, hashPassword, firstName, lastName) " +
            "values (?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("firstName"))
            .lastName(row.getString("lastName"))
            .email(row.getString("email"))
            .hashPassword(row.getString("hashPassword"))
            .build();

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getEmail(),
                entity.getHashPassword(),
                entity.getFirstName(),
                entity.getLastName());
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public List<User> findAllByIds(List<Long> ids) {
        return null;
    }
}
