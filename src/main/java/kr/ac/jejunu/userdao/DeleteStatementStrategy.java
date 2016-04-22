package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by sleepbear on 2016. 4. 22..
 */
public class DeleteStatementStrategy implements StatementStrategy {
    private long id;

    public DeleteStatementStrategy(long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user where id = ?");
        preparedStatement.setLong(1, id);
        return preparedStatement;

    }
}
