package repositories;


import models.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL_USERS = "select * from reg";
    //language=SQL
    private static final String SQL_FIND_ALL_USERS_BY_AGE = "select * from reg where age = ?";
    //language=SQL
    private static final String SQL_FIND_USER_BY_ID = "select * from reg where id = ?";
    //language=SQL
    private static final String SQL_INSERT_USER ="insert into reg(name, surname, age) values (?, ?, ?)";
    //language=SQL
    private static final String SQL_UPDATE_USER ="update reg set name=?, surname=?, age=? where id=?";
    //language=SQL
    private static final String SQL_DELETE_USER ="delete from reg where name=? and surname=? and age=?";
    //language=SQL
    private static final String SQL_DELETE_BY_ID ="delete from reg where id=?";

    private Connection connection;

    private SimpleJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
        this.jdbcTemplate = new SimpleJdbcTemplate(connection);
    }

    private RowMapper<User> usersRowMapper = row -> User.builder()
            .id(row.getInt("id"))
            .name(row.getString("name"))
            .surname(row.getString("surname"))
            .age(row.getInt("age"))
            .build();

    @Override
    public List<User> findAllByAge(Integer age) {
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS_BY_AGE, usersRowMapper, age);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jdbcTemplate.queryForList(SQL_FIND_USER_BY_ID, usersRowMapper, id).stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS, usersRowMapper);
    }

    @Override
    public boolean save(User entity) {
        return (jdbcTemplate.update(SQL_INSERT_USER,
                entity.getName(), entity.getSurname(), entity.getAge()) != 0);
    }

    @Override
    public boolean updateById(Long id, User entity) {
        return (jdbcTemplate.update(SQL_UPDATE_USER,
                entity.getName(), entity.getSurname(), entity.getAge(), id) != 0);
    }

    @Override
    public boolean delete(User entity) {
        return (jdbcTemplate.update(SQL_DELETE_USER,
                entity.getName(), entity.getSurname(), entity.getAge()) != 0);
    }

    @Override
    public boolean deleteById(Long id) {
        return (jdbcTemplate.update(SQL_DELETE_BY_ID, id) != 0);
    }
}
