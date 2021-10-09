package top.adxd.tikonlinejudge.executor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestFrequencyLimitAnnotion {
    @Autowired
    private FrequencyLimitService frequencyLimitService;
    @Test
    public void aaaasd(){
        System.out.println(frequencyLimitService.aaa());
        System.out.println(frequencyLimitService.aaa());
    }
}
