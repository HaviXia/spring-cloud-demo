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

        // 根据服务 id 获取实例
        List<ServiceInstance> instance = discoveryClient.getInstance("user-service");

        // 从实例中取出ip和端口

        ServiceInstance instance = instance.get(0);

        String url = "http://"+instance.getHost()+":"+instance.getPort()+"/user/" + id;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
}
