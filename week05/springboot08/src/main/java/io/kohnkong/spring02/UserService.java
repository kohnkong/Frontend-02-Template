package io.kohnkong.spring02;

import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月17日 22:58
 */
@Service
public class UserService {
    public String getUserById(Integer id){
        System.out.println("get...");
        return "User";
    }

    public void deleteUserById(Integer id){
        System.out.println("delete...");
    }
}
