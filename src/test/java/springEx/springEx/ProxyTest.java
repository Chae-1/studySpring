package springEx.springEx;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springEx.springEx.strategyAndProxy.Hello;
import springEx.springEx.strategyAndProxy.HelloTarget;

import static org.assertj.core.api.Assertions.assertThat;

public class ProxyTest {

    LogTest logTest ;
    @BeforeEach
    public void init() {
        ApplicationContext app = new AnnotationConfigApplicationContext(ProxyTestConfiguration.class);
        logTest = app.getBean("logTest", LogTest.class);
    }

    @Test
    public void test() {
        logTest.printS();
    }


    @Test
    public void proxyTest() {
        Hello hello = new HelloTarget();
        assertThat(hello.sayHello("hyeongil")).isEqualTo("hyeongil hello");
    }
}
