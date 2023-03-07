package springEx.springEx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyTestConfiguration {
    @Bean
    public LogTest logTest () {
        return new Proxy(new LogTest());
    }
}
