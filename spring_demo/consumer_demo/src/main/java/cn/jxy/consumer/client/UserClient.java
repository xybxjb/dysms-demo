package cn.jxy.consumer.client;

import cn.jxy.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 加鑫宇
 * @date 2020-04-11 18:41
 */

@FeignClient(value = "user-server",fallback =UserClientImpl.class)
public interface UserClient {

    @GetMapping("user/{id}")
    User queryById(@PathVariable("id")Long id);

}
