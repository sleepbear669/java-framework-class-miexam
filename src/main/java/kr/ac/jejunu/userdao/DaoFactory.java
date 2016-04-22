package kr.ac.jejunu.userdao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sleepbear on 2016. 4. 22..
 */
@Configuration
public class DaoFactory {


    @Bean
    public UserDao userDao() {
        return new UserDao(getConnectionMaker());
    }

    @Bean
    public SimpleConnectionMaker getConnectionMaker() {
        return new SimpleConnectionMaker();
    }

}
