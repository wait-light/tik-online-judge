package top.adxd.tikonlinejudge.auth.single;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.io.Serializable;

public enum AskStatus implements IEnum<Integer>, Serializable {
    //未审核
    NOT_APPROVED,
    //失败
    FAIL,
    //通过
    ADOPT,
    //授权失败
    PRIVILEGE_GRANT_FAILED;

    @Override
    public Integer getValue() {
        return ordinal();
    }
}
