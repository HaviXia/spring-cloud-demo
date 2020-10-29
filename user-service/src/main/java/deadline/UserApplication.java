package deadline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.discovery;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("deadline.user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
