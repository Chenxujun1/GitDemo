package cloud.controller;

import cloud.entity.User;
import cloud.service.IUserService;
import cloud.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private HttpClientUtils httpClientUtils;
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "getUserById/{id}")
    public User getUserById(@PathVariable String id){
        User user = userService.getUserById(id);
       return  user;
    }

    @RequestMapping("/getUser")
    public String getUser(){
        Map<String, String> param = new HashMap<>();
        param.put("id", "046314c6530611e996ef005056c00001");
        String response = httpClientUtils.doPost("http://localhost:22223/Movie-Application/user/findUserById", param);
        return  response;
    }
}
