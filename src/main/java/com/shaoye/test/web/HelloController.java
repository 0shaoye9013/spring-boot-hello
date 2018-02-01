package com.shaoye.test.web;

import com.shaoye.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.logging.Logger;

/**
 * @param
 * @Author: ShaoYe
 * @Description:
 * @Date:
 */
@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/hello")
    public String index() throws InterruptedException {
        ServiceInstance instance = client.getLocalServiceInstance();
        //处理线程等待几秒钟
        int sleepTime = new Random().nextInt(3000);
        logger.info("sleepTime:"+sleepTime);
        Thread.sleep(sleepTime);

        logger.info("/test/hello,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "Hello Spring Boot";
    }

    @GetMapping("/hello1")
    public String index1(@RequestParam String name){
        return "hello "+ name ;
    }

    @GetMapping("/hello2")
    public User index2(@RequestHeader String name,@RequestHeader Integer age){
        return new User(name,age);
    }
    @PostMapping("/hello3")
    public String index3(@RequestBody User user){
        return "hello "+ user.getName()+","+user.getAge() ;
    }

}
