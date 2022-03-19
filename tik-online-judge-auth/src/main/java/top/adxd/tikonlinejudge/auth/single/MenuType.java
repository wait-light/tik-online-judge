package top.adxd.tikonlinejudge.auth.single;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.io.Serializable;

public enum MenuType implements IEnum<Integer>, Serializable {
    /**
     * 目录
     */
    DIRECTORY,
    /**
     * 接口
     */
    INTERFACE,
    /**
     * 归纳，用于归类权限
     */
    INDUCTION;
    @Override
    public Integer getValue() {
        return ordinal();
    }
}
