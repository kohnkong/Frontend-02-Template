package io.kohnkong.title02.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月17日 21:12
 */
public class XmlBeanAssembleTest {
    public static void main(String[] args) {
        // 定义配置文件路径
        String xmlPath = "beans1.xml";
        // 加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        // 构造方式输出结果

        System.out.println(applicationContext.getBean("user1"));
        // 设值方式输出结果
        System.out.println(applicationContext.getBean("user2"));
    }
}
