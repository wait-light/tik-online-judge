package top.adxd.tikonlinejudge.executor.single;

import com.baomidou.mybatisplus.annotation.IEnum;

import java.io.Serializable;

/**
 * @author wait-light
 * @date 2021/10/23 下午5:02
 */

public enum Language implements IEnum<Integer>, Serializable {
    JAVA,
    CPP,
    C,
    PYTHON;

    @Override
    public Integer getValue() {
        return this.ordinal();
    }
}
