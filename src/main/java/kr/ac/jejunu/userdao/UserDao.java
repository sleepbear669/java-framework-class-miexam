package kr.ac.jejunu.userdao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        final String sql = "select * from user where id = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                final User fetchedUser = new User();
                fetchedUser.setId(rs.getLong("id"));
                fetchedUser.setName(rs.getString("name"));
                fetchedUser.setPassword(rs.getString("password"));
                return fetchedUser;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    public long add(User user) throws ClassNotFoundException, SQLException {
        final String sql = "INSERT INTO user(name, password) VALUES (? , ? )";
        final GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            return preparedStatement;
        }, generatedKeyHolder);

        return (long) generatedKeyHolder.getKey();
    }

    public void update(User user) {
        final String sql = "UPDATE user set name = ? , password = ? where id = ?";
        final Object[] params = {user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql, params);

    }

    public void delete(long id) {
        final String sql = "DELETE FROM user where id = ?";
        final Object[] params = {id};
        jdbcTemplate.update(sql, params);

    }

}
