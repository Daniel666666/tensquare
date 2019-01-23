package daniel.tensquare.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.JwtUtil;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
public class ManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
