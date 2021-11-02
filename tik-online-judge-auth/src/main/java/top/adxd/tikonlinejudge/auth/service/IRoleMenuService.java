package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
public interface IRoleMenuService extends IService<RoleMenu> {
    List<Long> userMenus(Long id);
}
