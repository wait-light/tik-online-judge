package top.adxd.tikonlinejudge.user.dto;

import lombok.Data;
import top.adxd.tikonlinejudge.user.entity.Menu;

import java.io.Serializable;
import java.util.List;

/**
 * @author wait_light
 * @create 2021/9/12
 */
@Data
public class MenuTree extends Menu implements Serializable {
    public List<MenuTree> children;
}
