package com.geekbang.JVM.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月01日 21:38
 */
public interface HttpResponseFilter {
    void filter(FullHttpResponse response);
}
