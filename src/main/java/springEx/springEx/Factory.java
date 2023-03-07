package springEx.springEx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import springEx.springEx.domain.User;
import springEx.springEx.v6.UserDaoV6;
import springEx.springEx.v6.UserService;

import javax.sql.DataSource;

import static springEx.springEx.domain.ConnectionStatus.*;

@Configuration
public class Factory {
    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(URL, USER, PASSWORD);
    }
    // aop, proxy
    @Bean
    public UserService userService() {
        return new UserService(userDaoV6());
    }

    @Bean
    public UserDaoV6 userDaoV6() {
        return new UserDaoV6(dataSource());
    }
}
