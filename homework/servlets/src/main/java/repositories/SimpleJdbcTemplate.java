package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleJdbcTemplate {

    private Connection connection;

    public SimpleJdbcTemplate(Connection connection) {
        this.connection = connection;
    }

    private PreparedStatement prepareStatement(String sql, Object... args) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int position = 1;
        for (Object arg : args) {
            preparedStatement.setObject(position, arg);
            position++;
        }
        return preparedStatement;
    }

    public <T> List<T> queryForList(String sql, RowMapper<T> rowMapper, Object... args) {
        try {
            ResultSet resultSet = null;

            PreparedStatement preparedStatement = prepareStatement(sql, args);

            resultSet = preparedStatement.executeQuery();

            List<T> result = new ArrayList<>();

            if (resultSet == null) {
                throw new SQLException("Empty result");
            }

            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> int update(String sql, Object... args) {
        try {
            int affectedRows = 0;

            PreparedStatement preparedStatement = prepareStatement(sql, args);

            affectedRows = preparedStatement.executeUpdate();

            return affectedRows;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
