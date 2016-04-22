package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by sleepbear on 2016. 4. 22..
 */
public class HallaUserDao extends UserDao{
    @Override
    Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/UserDatabase", "root", "gom0119!1");
    }
}
