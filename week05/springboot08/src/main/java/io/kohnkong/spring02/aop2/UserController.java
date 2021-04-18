package io.kohnkong.spring02.aop2;

import io.kohnkong.spring02.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 17:38
 */
@RestController
public class UserController {

    @GetMapping("getOne")
    @OperateLog(type = "select",operateObj = "user list")
    public void getOne() {
        System.out.println("UserController getOne()......");
    }
}
