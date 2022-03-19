package top.adxd.tikonlinejudge.auth.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import top.adxd.tikonlinejudge.auth.api.constant.SystemMenu;
import top.adxd.tikonlinejudge.auth.entity.Menu;
import top.adxd.tikonlinejudge.auth.service.IMenuService;
import top.adxd.tikonlinejudge.auth.single.MenuType;

//@Configuration
public class SystemMenuPersistenceConfig {
    @Autowired
    private IMenuService menuService;

    @Bean
    public Menu systemAutoGenerator(){
        Menu systemAutoGeneratorMenu = menuService.getOne(new QueryWrapper<Menu>().eq("name", SystemMenu.SYSTEM_AUTO_GENERATOR_MENU_NAME).last(" limit 1"));
        if (systemAutoGeneratorMenu == null){
            systemAutoGeneratorMenu = new Menu();
            systemAutoGeneratorMenu.setParentId(0L);
            systemAutoGeneratorMenu.setName(SystemMenu.SYSTEM_AUTO_GENERATOR_MENU_NAME);
            systemAutoGeneratorMenu.setPerms(SystemMenu.SYSTEM_AUTO_GENERATOR_MENU_NAME);
            systemAutoGeneratorMenu.setType(MenuType.DIRECTORY);
            menuService.save(systemAutoGeneratorMenu);
        }
        return systemAutoGeneratorMenu;
    }
}
