package com.tdkj.tdcloud.kibContent;

import com.tdkj.tdcloud.common.feign.annotation.EnableTdcloudFeignClients;
import com.tdkj.tdcloud.common.security.annotation.EnableTdcloudResourceServer;
import com.tdkj.tdcloud.common.swagger.annotation.EnableOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tdcloud archetype
 * <p>
 * 项目启动类
 */
@EnableOpenApi("kibContent")
@EnableTdcloudFeignClients
@EnableDiscoveryClient
@EnableTdcloudResourceServer
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
