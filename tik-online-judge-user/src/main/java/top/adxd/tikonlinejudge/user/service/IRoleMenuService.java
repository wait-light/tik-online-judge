package top.adxd.tikonlinejudge.user.service;

import top.adxd.tikonlinejudge.user.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-09-11
 */
public interface IRoleMenuService extends IService<RoleMenu> {
    List<Long> userMenus(Long id);
}
