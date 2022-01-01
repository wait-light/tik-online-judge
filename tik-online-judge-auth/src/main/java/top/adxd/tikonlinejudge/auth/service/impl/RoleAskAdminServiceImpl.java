package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.auth.entity.RoleAsk;
import top.adxd.tikonlinejudge.auth.service.IRoleAskAdminService;
import top.adxd.tikonlinejudge.auth.service.IRoleAskService;
import top.adxd.tikonlinejudge.auth.single.AskStatus;
import top.adxd.tikonlinejudge.common.util.PageUtils;
import top.adxd.tikonlinejudge.common.util.UserInfoUtil;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleAskAdminServiceImpl implements IRoleAskAdminService {

    @Autowired
    private IRoleAskService roleAskService;

    @Override
    public CommonResult list() {
        PageUtils.makePage();
        List<RoleAsk> status = roleAskService.list(new QueryWrapper<RoleAsk>()
                .eq("status", AskStatus.NOT_APPROVED.getValue()));
        return CommonResult.success().listData(status);
    }

    @Override
    public CommonResult examine(Long id, AskStatus askStatus) {
        RoleAsk roleAsk = roleAskService.getById(id);
        if (roleAsk == null) {
            return CommonResult.error("操作有误");
        }
        roleAsk.setStatus(askStatus);
        roleAsk.setUpdateTime(LocalDateTime.now());
        roleAsk.setHandler(UserInfoUtil.getUid());
        return roleAskService.updateById(roleAsk) ?
                CommonResult.success("操作成功") :
                CommonResult.error("操作失败");
    }
}
