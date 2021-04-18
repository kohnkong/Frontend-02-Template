package io.kohnkong.title02.annotation;

import org.springframework.stereotype.Repository;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月17日 21:39
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("userdao...save...");
    }
}
