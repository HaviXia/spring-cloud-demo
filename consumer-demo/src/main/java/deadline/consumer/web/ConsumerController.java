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
//
//    //动态注入 url
//    @Autowired

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("{id}")
    @HystrixCommand(fallbackMethod = "queryByIdFallBack") //开启服务降级容错处理

    /*
     * 成功和失败的两个函数函数名不限制，但是返回值、参数列表 必须 完全一致！！！
     * */
    public String queryById(@PathVariable("id") Long id) {
        String url = "http://user-service/user/" + id;

        String user = restTemplate.getForObject(url, String.class);
        return user;
    }

    public String queryByIdFallBack(@PathVariable("id") Long id) {
        return "服务器正忙,请稍后再试";
    }
    //Ribbon 负载均衡
    // private RibbonLoadBalancerClient client;
    // eureka 中存放的服务Instance是一个双层map
    /*
     * Applicatoin:USER-SERVICE    STATUS: 192.168.xx.xx:user-service:8081     ip:应用名:端口号
     *
     * 第二层的 map 是这个实例的对象本身
     * */

    // 根据服务 id 获取实例
    //  List<ServiceInstance> instance = discoveryClient.getInstance("user-service");
    // ServiceInstance instance = client.choose("user-service");

    // 从实例中取出ip和端口

    //ServiceInstance instance = instance.get(0);

    //   String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;
    //System.ou t.println("url = " + url);

}
