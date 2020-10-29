package deadline.consumer.web;

import deadline.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    //动态注入 url
    @Autowired
    private DiscoveryClient discoveryClient;
    discoveryClient.getInstance();

    @GetMapping("{id}")
    public User queryById(@PathVariable("id") Long id) {
        // eureka 中存放的服务Instance是一个双层map
        /*
        * Applicatoin:USER-SERVICE    STATUS: 192.168.xx.xx:user-service:8081     ip:应用名:端口号
        *
        * 第二层的 map 是这个实例的对象本身
        * */

        // 根据服务 id 获取实例
        List<ServiceInstance> instance = discoveryClient.getInstance("user-service");

        // 从实例中取出ip和端口

        ServiceInstance instance = instance.get(0);

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;
        System.out.println("url = " + url);
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
}
