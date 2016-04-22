package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {

    private JdbcContext jdbcContext;

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        return jdbcContext.JdbcContextWithStatementStrategyForQuery(id, connection);
    }

    public long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddStatementStrategy(user);
        return jdbcContext.JdbcContextWithStatementStrategyForInsert(statementStrategy);
    }

    public void update(User user) {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);

    }

    public void delete(long id) {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextWithStatementStrategyForUpdate(statementStrategy);

    }

}
