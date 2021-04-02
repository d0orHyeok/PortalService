package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// dependency injection
// spring framework 기능
@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    private JejuConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}