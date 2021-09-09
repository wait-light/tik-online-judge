package top.adxd.tikonlinejudge.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wait_light
 * @since 2021-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsUserSocial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 社交账号类型
     */
    private Long socialType;

    /**
     * 用户社交账号的唯一id
     */
    private String userSocialId;


}
