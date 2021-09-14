package top.adxd.tikonlinejudge.user.dto;

import lombok.Data;
import top.adxd.tikonlinejudge.user.entity.Role;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author wait_light
 * @create 2021/9/13
 */
@Data
public class RoleWithMenu extends Role implements Serializable {
    @NotNull
    public List<Long> roleMenus;
}
