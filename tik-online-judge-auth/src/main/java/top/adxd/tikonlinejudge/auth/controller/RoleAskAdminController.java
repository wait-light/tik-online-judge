package top.adxd.tikonlinejudge.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.adxd.tikonlinejudge.auth.service.IRoleAskAdminService;
import top.adxd.tikonlinejudge.auth.single.AskStatus;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

@RestController
@RequestMapping("/auth/role-ask-admin")
public class RoleAskAdminController {

    @Autowired
    private IRoleAskAdminService roleAskAdminService;

    @GetMapping("/list")
    public CommonResult list() {
        return roleAskAdminService.list();
    }

    @PutMapping("/{id}/{status}")
    public CommonResult examine(@PathVariable("id") Long id, @PathVariable("status") AskStatus askStatus) {
        return roleAskAdminService.examine(id, askStatus);
    }

}
