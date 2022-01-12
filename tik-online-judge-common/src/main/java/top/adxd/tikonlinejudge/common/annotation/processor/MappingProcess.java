package top.adxd.tikonlinejudge.common.annotation.processor;

import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.adxd.tikonlinejudge.auth.api.dto.Menu;
import top.adxd.tikonlinejudge.common.annotation.Role;
import top.adxd.tikonlinejudge.common.exeption.RoleExcludeException;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class MappingProcess {

    /**
     * 排除指定路径
     *
     * @param role           角色元信息
     * @param methodMappings 需要被过滤的列表
     */
    public void excludeSpecific(Role role, List<MethodMapping> methodMappings) {
        excludeSpecific(role.exclude(), methodMappings);
    }

    public void excludeSpecific(String[] exclude, List<MethodMapping> methodMappings) {
        for (String excludeStr : exclude) {
            MethodMapping exculdeMethodMapping = excludeParsing(excludeStr);
            for (int index = methodMappings.size() - 1; index >= 0; index--) {
                MethodMapping mapping = methodMappings.get(index);
                if (exculdeMethodMapping.path.equals(mapping.getPath()) && mapping.getRequestMethods().contains(exculdeMethodMapping.requestMethods)) {
                    methodMappings.remove(index);
                }
            }
        }
    }

    private MethodMapping excludeParsing(String exclude) {
        if (exclude == null) {
            throw new RoleExcludeException("不正确的exclude配置");
        }
        String[] split = exclude.split(":");
        if (split.length != 2) {
            throw new RoleExcludeException("不正确的exclude配置");
        }
        if (!isSupportMethod(split[0])) {
            throw new RoleExcludeException(String.format("方法【%s】不被支持", split[0]));
        }
        return new MethodMapping(split[1], split[0]);
    }

    private boolean isSupportMethod(String methodString) {
        if (methodString.toLowerCase(Locale.ROOT).equals("get")) {
            return true;
        }
        if (methodString.toLowerCase(Locale.ROOT).equals("post")) {
            return true;
        }
        if (methodString.toLowerCase(Locale.ROOT).equals("delete")) {
            return true;
        }
        if (methodString.toLowerCase(Locale.ROOT).equals("put")) {
            return true;
        }

        return false;
    }

    private Role getOneRole(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof Role) {
                return (Role) annotation;
            }
        }
        return null;
    }

    private List<RequestMapping> getRequestMappingList(Annotation[] annotations) {
        List<RequestMapping> requestMappings = new ArrayList<>();
        for (Annotation annotation : annotations) {
            if (annotation instanceof RequestMapping) {
                requestMappings.add((RequestMapping) annotation);
            }
        }
        return requestMappings;
    }

    public List<MethodMapping> mergeRequestMapping(RequestMapping prefix, List<RequestMapping> requestMappings) {
        return mergeRequestMapping(prefix, requestMappings.toArray(new RequestMapping[requestMappings.size()]));
    }

    /**
     * 合并类上的RequestMapping和方法上的RequestMapping
     *
     * @param prefix          前缀
     * @param requestMappings 后缀
     * @return 合并后的信息
     */
    private List<MethodMapping> mergeRequestMapping(RequestMapping prefix, RequestMapping[] requestMappings) {
        if (requestMappings == null || requestMappings.length == 0) {
            throw new UnsupportedOperationException("requestMappings不能为空");
        }
        List<MethodMapping> methodMappings = new ArrayList<>(requestMappings.length);
        String[] prefixPath = null;
        if (prefix == null) {
            prefixPath = new String[]{""};
        } else {
            prefixPath = prefix.path();
        }
        for (RequestMapping mapping : requestMappings) {
            String[] path = mapping.path();
            StringBuilder methodsStringBuilder = new StringBuilder();
            for (RequestMethod requestMethod : mapping.method()) {
                methodsStringBuilder.append(requestMethod.toString())
                        .append(",");
            }
            int index = methodsStringBuilder.lastIndexOf(",");
            methodsStringBuilder.deleteCharAt(index);
            String methodString = methodsStringBuilder.toString();
            for (String pre : prefixPath) {
                for (String end : path) {
                    MethodMapping methodMapping = new MethodMapping(pre + end, methodString);
                    methodMappings.add(methodMapping);
                }
            }
        }
        return methodMappings;
    }

    public static class MethodMapping {
        private String path;
        private String requestMethods;
        public static final String SPLIT_STRING = ":";

        public MethodMapping(String path, String requestMethods) {
            this.path = path;
            this.requestMethods = requestMethods;
        }

        public String getPath() {
            return path;
        }

        public String getRequestMethods() {
            return requestMethods;
        }

        @Override
        public String toString() {
            return "MethodMapping{" +
                    "path='" + path + '\'' +
                    ", requestMethods='" + requestMethods + '\'' +
                    '}';
        }

        public static Menu conver2Menu(String roleName, MethodMapping methodMapping) {
            Menu menu = new Menu();
            menu.setName(roleName + SPLIT_STRING + methodMapping.getRequestMethods() + SPLIT_STRING + methodMapping.getPath());
            menu.setPerms(roleName + SPLIT_STRING + methodMapping.getRequestMethods() + SPLIT_STRING + methodMapping.getPath());
            menu.setRequestMethod(methodMapping.getRequestMethods());
            menu.setUrl(methodMapping.getPath());
            return menu;
        }

    }

}
