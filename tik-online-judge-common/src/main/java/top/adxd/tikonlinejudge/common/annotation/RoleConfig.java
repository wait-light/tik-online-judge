package top.adxd.tikonlinejudge.common.annotation;

public class RoleConfig implements IRoleConfig{
    private String name;
    private Class[] target;
    private String[] exclude;

    public RoleConfig(String name, Class[] target, String[] exclude) {
        this.name = name;
        this.target = target;
        this.exclude = exclude;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class[] getTarget() {
        return target;
    }

    @Override
    public String[] getExclude() {
        return exclude;
    }
}
