package top.adxd.tikonlinejudge.auth.mapper;

import top.adxd.tikonlinejudge.auth.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wait_light
 * @since 2021-10-26
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    List<Long> getMenuIdsByRole(Long roleId);
}
