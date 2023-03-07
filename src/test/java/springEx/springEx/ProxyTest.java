package springEx.springEx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

}
