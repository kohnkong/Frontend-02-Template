package com.geekbang.JVM.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月01日 22:12
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("kk", "java-1-nio");
    }
}
