package top.adxd.onlinejudge.thirdpart.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.adxd.tikonlinejudge.common.vo.CommonResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/thirdpart/oss")
public class OssController {

    @Autowired
    OSS ossClient;

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.bucket}")
    private String bucket;
    @Value("${oss.access-key}")
    private String accessId;

    /**
     * @return 获取前端上传文件的信息
     */
    @GetMapping("/postPolicy")
    public CommonResult postPolicy() {
        String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = format + "/"; // 用户上传文件时指定的前缀。
        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);
            return CommonResult.success()
                    .add("accessid", accessId)
                    .add("policy", encodedPolicy)
                    .add("signature", postSignature)
                    .add("dir", dir)
                    .add("host", host)
                    .add("expire", String.valueOf(expireEndTime / 1000));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return CommonResult.error("获取出错");
    }
}
