package daniel.tensquare.friend.client.impl;

import daniel.tensquare.friend.client.UserClient;
import org.springframework.stereotype.Component;

@Component
public class UserClientImpl implements UserClient {
    @Override
    public void incFanscount(String userid, int x) {
        System.out.println("熔断器运行---");
    }

    @Override
    public void incFollowcount(String userid, int x) {
        System.out.println("熔断器运行---");
    }
}
