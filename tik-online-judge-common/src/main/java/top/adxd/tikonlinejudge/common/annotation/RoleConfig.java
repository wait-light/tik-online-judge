package top.adxd.tikonlinejudge.common.annotation;

public class RoleConfig implements IRoleConfig{
    private String name;
    private Class[] target;
    private String[] exclude;
    private Boolean append;
    public RoleConfig(String name, Class[] target, String[] exclude,Boolean append) {
        this.name = name;
        this.target = target;
        this.exclude = exclude;
        this.append = append;
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

    @Override
    public Boolean isAppend() {
        return append;
    }
}
