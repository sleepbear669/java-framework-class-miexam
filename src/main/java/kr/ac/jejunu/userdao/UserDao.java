package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    public long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(name, password) VALUES (? , ? )");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        final long lastAddedId = getLastAddedId(connection);
        preparedStatement.close();
        connection.close();

        return lastAddedId;
    }

    private long getLastAddedId(Connection connection) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT last_insert_id()");
        final ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        final long lastAddedId = resultSet.getLong(1);
        resultSet.close();
        preparedStatement.close();
        return lastAddedId;
    }
}
