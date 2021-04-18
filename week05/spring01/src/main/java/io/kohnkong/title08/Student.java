package io.kohnkong.title08;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 19:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable, BeanNameAware, ApplicationContextAware {


    private int id;
    private String name;

    private String beanName;
    private ApplicationContext applicationContext;

    public Student(int id, String name) {
        this.id=id;
        this.name = name;
    }

    public void init() {
        System.out.println("hello...........");
    }

//    public Student create(){
//        return new Student(101,"KK101");
//    }

    public void print() {
        System.out.println(this.beanName);
        //System.out.println("   context.getBeanDefinitionNames() ===>> "
        // + String.join(",", applicationContext.getBeanDefinitionNames()));

    }


}