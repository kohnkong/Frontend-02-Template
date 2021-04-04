package com.geekbang.JVM.gateway.router;

import java.util.List;
import java.util.Random;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月01日 21:40
 */
public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
    }
}
