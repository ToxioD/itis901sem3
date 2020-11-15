package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Photo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class PhotoCrudRepositoryImpl implements CrudRepository<Photo> {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from photos";
    //language=SQL
    private static final String SQL_INSERT = "insert into photos(path) values (?)";

    private JdbcTemplate jdbcTemplate;

    public PhotoCrudRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Photo> photoRowMapper = (row, rowNumber) -> Photo.builder()
            .id(row.getLong("id"))
            .path(row.getString("path"))
            .build();

    @Override
    public void save(Photo entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getPath());
    }

    @Override
    public void update(Photo entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Photo> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Photo> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, photoRowMapper);
    }

    @Override
    public List<Photo> findAllByIds(List<Long> ids) {
        return null;
    }
}

