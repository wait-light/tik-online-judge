package top.adxd.tikonlinejudge.executor;

import org.springframework.stereotype.Service;
import top.adxd.tikonlinejudge.executor.annotation.FrequencyLimit;

@Service
public class FrequencyLimitService {
    @FrequencyLimit(value = 1,name = "aaa")
    public String aaa(){
        return "asdasd";
    }
}
