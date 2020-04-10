package com.leyou.sms.utils.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jiaxinyu
 * @date 2019-11-15 19:40
 */

@ConfigurationProperties(prefix = "ly.sms")
public class SmsProperties {
    String accessKey;
    String accessKeySecret;
    String signName;
    String verifyCodeTemplate;

    public String getVerifyCodeTemplate() {
        return verifyCodeTemplate;
    }

    public void setVerifyCodeTemplate(String verifyCodeTemplate) {
        this.verifyCodeTemplate = verifyCodeTemplate;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSignName() {
        return signName;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getAccessKey() {
        return accessKey;
    }
}
