package com.geekbang.JVM.gateway.router;

import java.util.List;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月01日 21:40
 */
public interface HttpEndpointRouter {

    String route(List<String> endpoints);
}
