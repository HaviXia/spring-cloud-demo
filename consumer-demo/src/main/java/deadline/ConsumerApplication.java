package deadline;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


// 服务熔断,下面三个被  SpringCloudApplication 包含
//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication
@EnableFiegnClients
@SpringCloudApplication
public class ConsumerApplication {
    @Bean
    @LoadBalanced
    /*
     * 增加拦截器会拦截 RestTemplate 请求，
     * */
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
