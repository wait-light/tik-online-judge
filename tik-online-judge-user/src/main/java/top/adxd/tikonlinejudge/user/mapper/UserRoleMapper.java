package top.adxd.tikonlinejudge.user.mapper;

import top.adxd.tikonlinejudge.user.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wait_light
 * @since 2021-09-11
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Long> getRoleIdsByUserId(Long uid);
}
