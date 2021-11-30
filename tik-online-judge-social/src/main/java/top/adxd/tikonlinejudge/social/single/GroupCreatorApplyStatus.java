package top.adxd.tikonlinejudge.social.single;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum GroupCreatorApplyStatus implements IEnum<Integer> {
    UN_JUDGE,
    REFUSE,
    ACCEPT;
    @Override
    public Integer getValue() {
        return null;
    }
}
