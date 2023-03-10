package springEx.springEx.strategyAndProxy;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import springEx.springEx.domain.User;

public class TestService {
    private TestDao dao;

    public void doSomething() {
        dao.add(new User());
    }
}
