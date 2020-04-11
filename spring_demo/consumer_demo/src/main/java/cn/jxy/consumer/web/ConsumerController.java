package cn.jxy.consumer.web;

import cn.jxy.consumer.client.UserClient;
import cn.jxy.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 加鑫宇
 * @date 2020-04-10 23:04
 */
@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback="defaultFallback") //降级之后的默认处理方法
public class ConsumerController {
      @Autowired
      private UserClient userClient;

//
//    @Autowired
//    private DiscoveryClient discoveryClient;

//    最终 用feign来获取
    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userClient.queryById(id);
    }



//    熔断
//    @GetMapping("{id}")
////    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInmillisecounds",value="3000")})
//    @HystrixCommand(commandProperties = {@HystrixProperty(name="circuitBreaker.requestVolumThreshold",value="10"), //请求次数 默认20
//                                         @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"), //休眠时常 默认5000
//                                         @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="50")}) //触发熔断的失败请求最小占比，默认50%
//    public String queryById(@PathVariable("id") Long id) {
//        if(id==1){
//            throw new RuntimeException("太忙了");
//        }
//        String url = "http://user-server/user/" + id;
//        String user = restTemplate.getForObject(url, String.class);
//        return user;
//    }
//    //    服务降级   注意，这种不带参数
//    public String defaultFallback(){
//        return  "拥挤";
//    }



//    @Autowired
//    private RibbonLoadBalancerClient client;
//
//    @GetMapping("{id}")
//    public User queryById(@PathVariable("id") Long id) {
//        四
////      String url = "http://localhost:8081/user/"+id;
//
//      三
////      根据服务id获取实例
////      List<InstanceInfo> instances = discoveryClient.getInstancesById("user-server");
////      从实例中取出ip和端口
////      ServiceInstance instance = (ServiceInstance) instances.get(0);
//
////   方法二
////      ServiceInstance instance = client.choose("user_service");
////      String url = "http://"+instance.getHost()+":"+instance.getPort()+"/user/"+id;
//   初始的
//        String url = "http://user-server/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }

//服务降级方法一
//    @Autowired
//    private RestTemplate restTemplate;
//    @GetMapping("{id}")
//    @HystrixCommand(fallbackMethod="queryByIdFallback")
//    public String queryById(@PathVariable("id") Long id) {
//        String url = "http://user-server/user/" + id;
//        String user = restTemplate.getForObject(url, String.class);
//        return user;
//    }
//    //    服务降级
//    public String queryByIdFallback(Long id){
//        return  "拥挤";
//    }

}
