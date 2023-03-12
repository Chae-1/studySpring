package springEx.springEx.strategyAndProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UpperCaseHandler implements InvocationHandler {
    private Hello target;

    public UpperCaseHandler(Hello target) {
        this.target = target;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String str = (String)method.invoke(target, args);
        return str.toUpperCase();
    }
}
