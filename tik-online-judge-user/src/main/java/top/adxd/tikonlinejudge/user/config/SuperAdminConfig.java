package top.adxd.tikonlinejudge.user.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.adxd.tikonlinejudge.user.config.property.SuperAdminProperties;
import top.adxd.tikonlinejudge.user.entity.User;
import top.adxd.tikonlinejudge.user.service.IUserService;

import java.time.LocalDateTime;

/**
 * @author wait_light
 * @create 2021/9/11
 */
@Configuration
public class SuperAdminConfig {

    @Autowired
    private IUserService userService;

    @Bean
    public User superAdmin(@Autowired SuperAdminProperties superAdminProperties) {
        User superAdmin = userService.getOne(new QueryWrapper<User>().eq("username", superAdminProperties.getUsername()));
        //创建默认管理员
        if (superAdmin == null) {
            superAdmin = new User();
            superAdmin.setUsername(superAdminProperties.getUsername());
            superAdmin.setPassword(superAdminProperties.getPassword());
            superAdmin.setStatus(true);
            superAdmin.setNickname("tik-admin");
            superAdmin.setAvatar(superAdminProperties.getAvatar());
            LocalDateTime now = LocalDateTime.now();
            superAdmin.setCreateTime(now);
            superAdmin.setUpdateTime(now);
            userService.save(superAdmin);
        }
        return superAdmin;
    }
}
