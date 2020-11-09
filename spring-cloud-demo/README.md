# spring-cloud-demo
Springcloud学习之旅

2019/11/07

1.创建了eureka-server作为服务注册中心</br>
2.创建了user-module作为服务提供者(provider)</br>
3.创建了consumer-demo作为消费者(consumer)</br>

遇到的问题:</br>
1.provider正常工作需要引用web组件，否则无法成功注册，原因不明</br>
2.consumer使用restTemplate正常工作需要引用ribbon组件，否则找不到服务，怀疑与@LoadBalance负载均衡有关</br>

下阶段目标：</br>
1.eureka服务注册权限控制</br>
2.实现jwt+redis登录功能</br>
