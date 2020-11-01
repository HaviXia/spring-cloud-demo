package deadline.consumer.client;


import deadline.consumer.pojo.User;

import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public String queryById(Long id) {
        return "未知的用户！";
    }
}
