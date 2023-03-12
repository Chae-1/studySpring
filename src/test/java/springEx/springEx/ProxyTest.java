package springEx.springEx;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springEx.springEx.strategyAndProxy.Hello;
import springEx.springEx.strategyAndProxy.HelloTarget;
import springEx.springEx.strategyAndProxy.UpperCaseHandler;
import springEx.springEx.v6.Message;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

public class ProxyTest {

    LogTest logTest ;
    ApplicationContext app;
    @BeforeEach
    public void init() {
        app = new AnnotationConfigApplicationContext(Factory.class);
    }

    @Test
    public void test() {
        logTest.printS();
    }


    @Test
    public void proxyTest() {
        Hello proxiedHello = (Hello)Proxy.newProxyInstance(
                Hello.class.getClassLoader(),
                new Class[] {Hello.class},
                new UpperCaseHandler(new HelloTarget()));
        System.out.println(proxiedHello.sayThankYou("hello"));


        Hello hello = new HelloTarget();
        assertThat(hello.sayHello("hyeongil")).isEqualTo("hyeongil hello");
    }

    @Test
    void isMessage() {
        Object message = app.getBean("message");

        assertThat(message).isInstanceOf(Message.class);
    }
}
