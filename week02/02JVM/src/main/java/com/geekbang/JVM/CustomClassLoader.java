package com.geekbang.JVM;

import java.io.*;

/**
 * @Description: 自定义类加载器
 * @author: kongqf
 * @date: 2021年03月20日 17:52
 */
public class CustomClassLoader extends ClassLoader {
    // 指定加载类的基本路径
    private String basPath;
    // 加载文件的扩展名
    private final String FILETYPE = ".xlass";

    public CustomClassLoader() {
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }

    /**
     * 加载二进制文件
     * @param name 文件名，不含扩展名
     * @return
     */
    private byte[] loadClassData(String name) {
        InputStream in = null;
        byte[] data = null;
        ByteArrayOutputStream bos = null;
        try {
            name = name.replace(".", "\\");
            in = new FileInputStream(new File(basPath + name + FILETYPE));
            bos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = in.read())) {
                ch = 255-ch; //解密
                bos.write(ch);
            }
            data = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public void setBasPath(String basPath) {
        this.basPath = basPath;
    }
}
