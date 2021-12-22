package top.adxd.tikonlinejudge.social.single;

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
