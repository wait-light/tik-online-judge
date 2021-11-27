package top.adxd.tikonlinejudge.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.adxd.tikonlinejudge.common.filter.ClearPageFilter;

import javax.servlet.Servlet;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({Servlet.class})
public class CommonAutoConfiguration {
    @Bean
    public ClearPageFilter filter() {
        return new ClearPageFilter();
    }
}
