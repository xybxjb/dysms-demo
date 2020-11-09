package cn.slipbend.service.impl;

import cn.slipbend.service.UserSigService;
import com.tencentyun.TLSSigAPIv2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:const.properties")
public class UserSigServiceImpl implements UserSigService {

    @Value("${IMConfig-sdkAppId}")
    private long sdkAppId;

    @Value("${IMConfig-secretKey}")
    private String secretKey;

    private long expire = 60*60*24*7;

    @Override
    public String generateUserSig(String userId) {
        TLSSigAPIv2 api = new TLSSigAPIv2(sdkAppId, userId);
        return api.genSig(userId,expire);
    }
}
