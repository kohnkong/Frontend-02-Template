package io.kohnkong.title02.annotation;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月17日 21:40
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    //当于配置文件中的:<property name="userDao"  ref="userDao"/>
    private UserDao userDao;

    @Override
    public void save() {
        //调用userDao中的save方法
        this.userDao.save();
        System.out.println("userservice....save...");
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
