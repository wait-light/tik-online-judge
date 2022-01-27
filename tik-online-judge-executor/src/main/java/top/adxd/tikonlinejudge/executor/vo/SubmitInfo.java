package top.adxd.tikonlinejudge.executor.vo;

import lombok.Data;
import top.adxd.tikonlinejudge.executor.entity.Submit;

@Data
public class SubmitInfo extends Submit {
    public String problemName;
    public String userName;
}
