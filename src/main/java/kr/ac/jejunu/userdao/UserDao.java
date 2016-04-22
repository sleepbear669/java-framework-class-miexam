package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {

    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        final String sql = "select * from user where id = ?";
        return jdbcContext.JdbcContextWithStatementStrategyForQuery(c -> {
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            return preparedStatement;
        });
    }

    public long add(User user) throws ClassNotFoundException, SQLException {
        final String sql = "INSERT INTO user(name, password) VALUES (? , ? )";
        return jdbcContext.JdbcContextWithStatementStrategyForInsert(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        });
    }

    public void update(User user) {
        final String sql = "UPDATE user set name = ? , password = ? where id = ?";
        final Object[] params = {user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, params);

    }

    public void delete(long id) {
        final String sql = "DELETE FROM user where id = ?";
        final Object[] params = {id};
        jdbcContext.update(sql, params);

    }

}
