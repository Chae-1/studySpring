package springEx.springEx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.MailSender;
import org.springframework.transaction.PlatformTransactionManager;
import springEx.springEx.v6.*;

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
        return new UserServiceTx(userServiceImpl(), transactionManager());
    }

    @Bean
    public UserService userServiceImpl() {
        return new UserServiceImpl(userDaoV6(), dataSource(), mailSender());
    }

    @Bean
    public UserDaoV6 userDaoV6() {
        return new UserDaoV6(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public MessageFactoryBean message() {
        return new MessageFactoryBean("Factory Bean");
    }
    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }
}
