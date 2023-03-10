package springEx.springEx.strategyAndProxy;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import springEx.springEx.domain.User;

public class TestServiceTx extends TestService{
    private TestService testService;

    public void doSomething() {
        PlatformTransactionManager manager = new DataSourceTransactionManager(new DriverManagerDataSource());
        TransactionStatus transaction = manager.getTransaction(new DefaultTransactionDefinition());
        try {
            testService.doSomething();
            manager.commit(transaction);
        } catch(Exception e) {
            manager.rollback(transaction);
        }
    }
}
