package top.adxd.tikonlinejudge.common.annotation.processor;


import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.auth.api.IRoleService;
import top.adxd.tikonlinejudge.auth.api.dto.Menu;
import top.adxd.tikonlinejudge.common.annotation.Role;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RoleProcessor implements IRoleProcessor {
    private Logger logger = LoggerFactory.getLogger(RoleProcessor.class);
    @DubboReference
    private IRoleService roleService;


    private List<Class> pendingClass;
    @Autowired
    private RoleCommonProcess roleCommonProcess;

    @Override
    public void setPendingClass(List<Class> pendingClass) {
        this.pendingClass = pendingClass;
    }

    @Override
    public void process() {
        logger.info("自动生成角色权限信息{}个", pendingClass.size());
        for (Class c : pendingClass) {
            Role role = AnnotationUtils.getAnnotation(c, Role.class);
            List<Class> targetClasses = new ArrayList<>();
            targetClasses.addAll(Arrays.asList(role.target()));
            targetClasses.add(c);
            List<MappingProcess.MethodMapping> process = roleCommonProcess.process(targetClasses, Arrays.asList(role.exclude()));
            List<Menu> collect = process.stream()
                    .map(item -> MappingProcess.MethodMapping.conver2Menu(role.name(), item))
                    .collect(Collectors.toList());
            roleService.addRoleWithMenu(role.name(), collect);
        }
    }


}
