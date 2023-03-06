package springEx.springEx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import springEx.springEx.domain.ConnectionStatus;
import springEx.springEx.v4.UserDaoV4;
import springEx.springEx.v5.UserDaoV5;
import springEx.springEx.v6.UserDaoV6;

import javax.sql.DataSource;

import static springEx.springEx.domain.ConnectionStatus.*;

@Configuration
public class Factory {
    @Bean
    public DataSource connectionMaker() {
        return new DriverManagerDataSource(URL, USER, PASSWORD);
    }

    @Bean
    public UserDaoV6 userDaoV6() {
        return new UserDaoV6(connectionMaker());
    }
}
