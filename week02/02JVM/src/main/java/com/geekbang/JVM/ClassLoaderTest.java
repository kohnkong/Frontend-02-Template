package com.geekbang.JVM;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年03月20日 18:24
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        //解密
        //File src = new File("src/main/resources/Hello.xlass");
        //File des = new File("src/main/resources/Hello.class");
        //EncryptUtil.encrypt(src, des);

        //作业2
        CustomClassLoader loader = new CustomClassLoader();
        //指定自定义类加载器加载xlass路径
        loader.setBasPath("src/main/resources/");
        //指定加载Hello类
        Class<?> clazz = loader.findClass("Hello");
        //System.out.println(clazz.getClassLoader());//输出加载类Hello的加载器
        //Object object = clazz.newInstance();//创建Hello类对象，会调用构造方法
        //clazz.getDeclaredMethods()[0].invoke(clazz.newInstance());
        //执行 hello 方法
        clazz.getMethod("hello").invoke(clazz.newInstance());
    }
}
