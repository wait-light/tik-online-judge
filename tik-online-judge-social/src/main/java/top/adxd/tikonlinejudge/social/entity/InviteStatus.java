package top.adxd.tikonlinejudge.social.entity;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum InviteStatus implements IEnum<Integer> {
    //未处理
    UNTREATED,
    //接受
    ACCEPT,
    //拒绝
    REJECT;

    @Override
    public Integer getValue() {
        return ordinal();
    }
}
