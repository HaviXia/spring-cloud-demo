package deadline.consumer.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service", fallback = UserClientFallback.class)
public interface UserClient {
    /*
     * 告诉 feign 几个参数，请求的方式、请求的路径、传入的参数、返回的结果
     * */
    @GetMapping("user/{id}")
    String queryById(@PathVariable("id") Long id);
}
