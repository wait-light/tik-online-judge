package top.adxd.tikonlinejudge.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.adxd.tikonlinejudge.auth.dto.AskDto;
import top.adxd.tikonlinejudge.auth.entity.Role;
import top.adxd.tikonlinejudge.auth.entity.RoleAsk;
import top.adxd.tikonlinejudge.auth.entity.UserRole;
import top.adxd.tikonlinejudge.auth.mapper.RoleAskMapper;
import top.adxd.tikonlinejudge.auth.service.IRoleAskAdminService;
import top.adxd.tikonlinejudge.auth.service.IRoleAskService;
import top.adxd.tikonlinejudge.auth.service.IRoleService;
import top.adxd.tikonlinejudge.auth.service.IUserRoleService;
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
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private RoleAskMapper roleAskMapper;


    @Override
    public CommonResult list() {
        PageUtils.makePage();
        List<AskDto> untreated = roleAskMapper.getUntreated();
        return CommonResult.success().listData(untreated);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = PermissionCacheServiceImpl.USER_CACHE_VALUE,key = "#id",condition = "#askStatus == AskStatus.ADOPT")
    @Override
    public CommonResult examine(Long id, AskStatus askStatus) {
        RoleAsk roleAsk = roleAskService.getById(id);

        if (roleAsk == null) {
            return CommonResult.error("操作有误");
        }
        if (askStatus == AskStatus.ADOPT) {
            Role role = roleService.getOne(new QueryWrapper<Role>().eq("name", roleAsk.getName()));
            if (role == null) {
                askStatus = AskStatus.PRIVILEGE_GRANT_FAILED;
            } else {
                UserRole userRole = new UserRole();
                userRole.setRoleId(role.getId());
                userRole.setUid(roleAsk.getUid());
                userRoleService.save(userRole);
            }
        }
        roleAsk.setStatus(askStatus);
        roleAsk.setUpdateTime(LocalDateTime.now());
        roleAsk.setHandler(UserInfoUtil.getUid());
        return roleAskService.updateById(roleAsk) ?
                CommonResult.success(askStatus == AskStatus.PRIVILEGE_GRANT_FAILED ? "通过，但是角色名不存在" : "操作成功") :
                CommonResult.error("操作失败");
    }
}
