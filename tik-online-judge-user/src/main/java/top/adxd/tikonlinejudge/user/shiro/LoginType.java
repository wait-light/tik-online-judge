package top.adxd.tikonlinejudge.user.shiro;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * IEnum<Integer>实现此接口可以方便使用Jack序列化
 *
 * @author light
 */

@Getter
public enum LoginType implements IEnum<String> {
    EMAIL,
    PASSWORD;
    @Override
    public String toString() {
        return this.name();
    }

    @Override
    public String getValue() {
        return this.name();
    }
}
