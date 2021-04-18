package io.kohnkong.title02.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sound.midi.Soundbank;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月17日 21:46
 */
public class AnnotationAssembleTest {

    public static void main(String[] args) {
        // 定义配置文件路径
        String xmlPath = "beans2.xml";
        // 加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        // 获取UserController实例
        UserController userController = (UserController) applicationContext.getBean("userController");
        // 调用UserController中的save()方法
        userController.save();
    }
}
