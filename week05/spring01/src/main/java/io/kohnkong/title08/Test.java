package io.kohnkong.title08;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 19:27
 */
public class Test {
    public static void main(String[] args) {
        // 定义配置文件路径
        String xmlPath = "beans4.xml";
        // 加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

        Student student123 = new Student(123, "KK123", "student123", applicationContext);
        System.out.println(student123.toString());
        student123.print();
        Student student100 = new Student(100, "KK100", "student100", applicationContext);
        System.out.println(student100.toString());

        student100.print();

        List<Student> students = new ArrayList<>();
        students.add(student100);
        students.add(student123);

        Klass class1 = new Klass();
        class1.setStudents(students);

        System.out.println(class1);
        System.out.println("Klass对象AOP代理后的实际类型：" + class1.getClass());
        System.out.println("Klass对象AOP代理后的实际类型是否是Klass子类：" + (class1 instanceof Klass));
        class1.dong();
        School school = new School();
        school.setClass1(class1);
        school.setStudent100(student100);
        school.ding();
    }
}
