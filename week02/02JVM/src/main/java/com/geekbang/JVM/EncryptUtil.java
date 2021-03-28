package com.geekbang.JVM;

import java.io.*;

/**
 * @Description: 加密文件
 * @author: kongqf
 * @date: 2021年03月20日 18:20
 */
public class EncryptUtil {
    /**
     * @param src:
     * @param des:
     * @Description: 加密后的文件
     * @author: kongqf
     * @date: 2021/3/20 19:35
     * @Return: void
     */
    public static void encrypt(File src, File des) throws Exception {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(des);

        int x;
        while (-1 != (x = in.read())) {
            x = 255 - x;//加密，255-X
            out.write(x);
        }
        in.close();
        out.close();
    }
}
