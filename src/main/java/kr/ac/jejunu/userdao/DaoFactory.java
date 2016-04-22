package kr.ac.jejunu.userdao;

/**
 * Created by sleepbear on 2016. 4. 22..
 */
public class DaoFactory {

    public UserDao userDao() {
        return new UserDao(new SimpleConnectionMaker());
    }

    public ProductDao productDao() {
        return new ProductDao(new SimpleConnectionMaker());

    }
}
