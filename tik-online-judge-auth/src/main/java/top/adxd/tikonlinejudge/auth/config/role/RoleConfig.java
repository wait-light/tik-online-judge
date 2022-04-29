package top.adxd.tikonlinejudge.auth.config.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import top.adxd.tikonlinejudge.auth.service.IRoleService;

@Configuration
public class RoleConfig {
    @Autowired
    private IRoleService roleService;

}
