package cloud.controller;

import cloud.entity.User;
import cloud.service.IUserService;
import cloud.util.HttpClientUtils;
import cloud.util.RedisUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private HttpClientUtils httpClientUtils;
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @RequestMapping(value = "getUserById/{id}")
    public User getUserById(@PathVariable String id){
        User user = userService.getUserById(id);
       return  user;
    }

    @RequestMapping("/getUser")
    public String getUser(){
        Map<String, Object> param = new HashMap<>();
        param.put("id", "046314c6530611e996ef005056c00001");
        String response = httpClientUtils.doPost("http://localhost:22223/Movie-Application/user/findUserById", param);
        return  response;
    }

    @RequestMapping(value = "/findUsersByPage", method = RequestMethod.GET)
    public List<User> findUsersByPage(@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                                          @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        redisUtil.set("123","123");
        return userService.findUsersByPage(currentPage, pageSize);
    }
}
