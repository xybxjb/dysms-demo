package cn.jxy.consumer.web;

import cn.jxy.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 加鑫宇
 * @date 2020-04-10 23:04
 */
@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("{id}")
    public User queryById(@PathVariable("id")Long id){
         String url = "http://localhost:8081/user/"+id;
         User user = restTemplate.getForObject(url,User.class);
         return user;
    }
}
