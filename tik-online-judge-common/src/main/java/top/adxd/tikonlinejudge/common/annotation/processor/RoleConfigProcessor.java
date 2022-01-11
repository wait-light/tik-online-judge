package top.adxd.tikonlinejudge.common.annotation.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.adxd.tikonlinejudge.auth.api.IRoleService;
import top.adxd.tikonlinejudge.common.annotation.IRoleConfig;

import java.util.*;

/**
 * 处理IRoleConfig，将其注入到数据库中
 */
@Component
public class RoleConfigProcessor implements IRoleConfigProcessor {
    private Logger logger = LoggerFactory.getLogger(RoleConfigProcessor.class);
    private List<IRoleConfig> roleConfigs;
    @Autowired
    private RoleCommonProcess roleCommonProcess;
    @Override
    public void setPendingConfig(List<IRoleConfig> roleConfigs) {
        this.roleConfigs = roleConfigs;
    }

    @Override
    public void process() {
        for (IRoleConfig roleConfig : roleConfigs) {
            List<MappingProcess.MethodMapping> process = roleCommonProcess.process(roleConfig.getTarget(), roleConfig.getExclude());

        }
    }
}
