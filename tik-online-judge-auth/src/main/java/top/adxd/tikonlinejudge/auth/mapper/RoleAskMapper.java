package top.adxd.tikonlinejudge.auth.mapper;

import org.apache.ibatis.annotations.Select;
import top.adxd.tikonlinejudge.auth.dto.AskDto;
import top.adxd.tikonlinejudge.auth.entity.RoleAsk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wait_light
 * @since 2021-12-27
 */
public interface RoleAskMapper extends BaseMapper<RoleAsk> {
    @Select("SELECT ask.* , u.nickname FROM ums_role_ask ask LEFT JOIN ums_user u ON ask.uid = u.uid where ask.status = 0")
    List<AskDto> getUntreated();
}
