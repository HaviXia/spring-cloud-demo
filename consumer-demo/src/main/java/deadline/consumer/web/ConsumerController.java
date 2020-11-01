package deadline.consumer.web;

import deadline.consumer.client.UserClient;
import deadline.consumer.pojo.User;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer")
//不能在每个方法上都写服务降级的逻辑
@DefaultProperties(defaultFallback = "queryByIdFallBack")
public class ConsumerController {
//    @Autowired
//    private RestTemplate restTemplate;
//
//    //动态注入 url
//    @Autowired

    @Autowired
    private UserClient userClient;

//    @Autowired
//    private DiscoveryClient discoveryClient;


    //  @HystrixCommand //开启服务降级容错处理

    /*
     * 成功和失败的两个函数函数名不限制，但是返回值、参数列表 必须 完全一致！！！
     * */
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") //时间由1s变成2s
//    })
    // @HystrixCommand
    @GetMapping("{id}")
    public String queryById(@PathVariable("id") Long id) {
        //增加熔断，手动控制
//        if (id % 2 == 0) {
//            throw new RuntimeException("");
//        }

        return userClient.queryById(id);
//
//        String user = restTemplate.getForObject(url, String.class);
//        return user;
    }

    public String queryByIdFallBack(@PathVariable("id") Long id) {
        return "服务器正忙,请稍后再试";
    }

    public String queryByIdFallBack() {
        return "服务 器正忙,请稍后再试";
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
