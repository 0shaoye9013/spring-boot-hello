package com.shaoye.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @param
 * @Author: ShaoYe
 * @Description:
 * @Date:
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/hello")
    public String index(){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/test/hello,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "Hello Spring Boot";
    }
}
