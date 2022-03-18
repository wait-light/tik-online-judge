package top.adxd.tikonlinejudge.social.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import top.adxd.tikonlinejudge.social.entity.Solution;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class SolutionDto extends Solution {
    private static final long serialVersionUID = 1L;
    public String username;
}
