package top.adxd.tikonlinejudge.executor.config.role;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.adxd.tikonlinejudge.common.annotation.IRoleConfig;
import top.adxd.tikonlinejudge.common.annotation.RoleConfig;
import top.adxd.tikonlinejudge.executor.controller.CollectionGroupController;

@Configuration
public class TestRoleConfig {
    @Bean
    public IRoleConfig roleConfig() {
        RoleConfig roleConfig = new RoleConfig("just test", new Class[]{CollectionGroupController.class}, new String[]{});
        return roleConfig;
    }
}
