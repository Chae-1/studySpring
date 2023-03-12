package springEx;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import springEx.springEx.strategyAndProxy.TransactionHandler;

import java.lang.reflect.Proxy;

@Getter
@Setter
public class TxProxyFactoryBean implements FactoryBean<Object> {
    private Object target;
    private PlatformTransactionManager manager;
    private String pattern;
    private Class<?> serviceInterface;

    public TxProxyFactoryBean(Object target) {
        this.target = target;
    }

    @Override
    public Object getObject() throws Exception {
        TransactionHandler handler = new TransactionHandler(target);
        handler.setPattern(pattern);
        handler.setTransactionManager(manager);

        return Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class[]{serviceInterface},
                handler);
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
