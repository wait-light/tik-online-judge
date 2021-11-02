package top.adxd.tikonlinejudge.auth.service;

import top.adxd.tikonlinejudge.auth.dto.MenuTree;
import top.adxd.tikonlinejudge.auth.entity.Menu;
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
public interface IMenuService extends IService<Menu> {
    List<MenuTree> menuTree();
}
