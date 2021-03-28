package com.geekbang.JVM;

import com.geekbang.JVM.utils.OkHttpUtil;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年03月28日 17:22
 */
public class OkhttpTest {
    public static void main(String[] args) {
        OkHttpUtil.get("http://localhost:8801", null);
    }
}
