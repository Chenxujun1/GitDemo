package com.cxj.cloud.controller;

import com.cxj.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${User.userServiceUrl}")
    private String  USERSERVICEURL;

    @RequestMapping(value = "findUserById")
    public User findUserById(String id){
        return this.restTemplate.getForObject(USERSERVICEURL + id, User.class);
    }
}
