package top.adxd.tikonlinejudge.auth.dto;

import lombok.Data;
import top.adxd.tikonlinejudge.auth.entity.Menu;

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
