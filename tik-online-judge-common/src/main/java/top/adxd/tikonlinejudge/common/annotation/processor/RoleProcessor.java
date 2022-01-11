package top.adxd.tikonlinejudge.common.annotation.processor;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.auth.api.IRoleService;
import top.adxd.tikonlinejudge.common.annotation.Role;
import java.util.*;

@Component
public class RoleProcessor implements IRoleProcessor {
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
        for (Class c : pendingClass) {
            Role role = AnnotationUtils.getAnnotation(c, Role.class);
            List<Class> targetClasses = new ArrayList<>();
            targetClasses.addAll( Arrays.asList(role.target()));
            targetClasses.add(c);
            List<MappingProcess.MethodMapping> process = roleCommonProcess.process(targetClasses, Arrays.asList(role.exclude()));


        }
    }


}
