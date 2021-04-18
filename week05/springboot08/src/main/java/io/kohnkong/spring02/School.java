package io.kohnkong.spring02;

import io.kohnkong.spring02.aop.ISchool;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月17日 22:25
 */
@Data
public class School implements ISchool {
    // Resource
    @Autowired(required = true) //primary
     Klass class1;

    @Autowired
    Student student100;

    @Override
    public void ding() {
        System.out.println("Class1 have...... students and one is ....." );

    }
}
