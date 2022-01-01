package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.adxd.tikonlinejudge.auth.entity.RoleAsk;
import top.adxd.tikonlinejudge.auth.mapper.RoleAskMapper;
import top.adxd.tikonlinejudge.auth.service.IRoleAskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.single.AskStatus;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wait_light
 * @since 2021-12-27
 */
@Service
public class RoleAskServiceImpl extends ServiceImpl<RoleAskMapper, RoleAsk> implements IRoleAskService {

    @Override
    public CommonResult ask(RoleAsk roleAsk) {
        LocalDateTime now = LocalDateTime.now();
        Long uid = UserInfoUtil.getUid();

        //检测是否有相同请求
        RoleAsk hasAsk = getOne(new QueryWrapper<RoleAsk>().select("id")
                .eq("uid", uid)
                .eq("name", roleAsk.getName()));
        if (hasAsk != null) {
            return CommonResult.permissionDeny("请不要重复申请，等待审核");
        }

        roleAsk.setCreateTime(now);
        roleAsk.setStatus(AskStatus.NOT_APPROVED);
        roleAsk.setUid(uid);
        roleAsk.setUpdateTime(now);
        return save(roleAsk) ?
                CommonResult.success("申请成功，等待审核") :
                CommonResult.error("申请失败");
    }
}
