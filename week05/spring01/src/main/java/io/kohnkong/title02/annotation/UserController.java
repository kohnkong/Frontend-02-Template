package io.kohnkong.title02.annotation;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月17日 21:44
 */
@Controller("userController")
//相当于配置文件中的:<bean id="userController" class="io.kohnkong.title02.annotation.UserController" />
public class UserController {


    //当于配置文件中的:<property name="userService"  ref="userService"/>
    @Resource(name="userService")
    private UserService userService;

    public void save(){
        this.userService.save();
        System.out.println("userController...save...");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
