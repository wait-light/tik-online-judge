package top.adxd.tikonlinejudge.social.entity;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum GroupUserType implements IEnum<Integer> {
    COMMON,
    MASTER;

    @Override
    public Integer getValue() {
        return ordinal();
    }
}
