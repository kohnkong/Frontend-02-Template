## Week03作业

Reactor 单线程模型
Reactor 多线程模型
Reactor 主从模型

#### 1.（必做）整合你上次作业的 httpclient/okhttp；
   * NettyHttpServer内服访问服务已替换成okhttp
#### 4.（选做）实现路由
   * NettyServerApplication 已测试
    
##### NioEventLoop 是 Netty 的 Reactor 线程，其角色
1. Boss Group：作为服务端 Acceptor 线程，用于 accept 客户端链接，并转发给 WorkerGroup 中的线程。
2. Worker Group：作为 IO 线程，负责 IO 的读写，从 SocketChannel 中读取报文或向 SocketChannel 写入报文。
3. Task Queue／Delay Task Queue：作为定时任务线程，执行定时任务，例如链路空闲检测和发送心跳消息等。

#### 基于Netty构建网关
1. 创建 ServerBootstrap 实例，ServerBootstrap 是 Netty 服务端的启动辅助类。  HttpInboundServer
2. 设置并绑定 Reactor 线程池，EventLoopGroup 是 Netty 的 Reactor 线程池，EventLoop 负责所有注册到本线程的 Channel。 HttpInboundServer
3. 设置并绑定服务器 Channel，Netty Server 需要创建 NioServerSocketChannel 对象。 HttpInboundServer
4. TCP 链接建立时创建 ChannelPipeline，ChannelPipeline 本质上是一个负责和执行 ChannelHandler 的职责链。HttpInboundInitializer
5. 添加并设置 ChannelHandler，ChannelHandler 串行的加入 ChannelPipeline 中。
6. 绑定监听端口并启动服务端，将 NioServerSocketChannel 注册到 Selector 上。
7. Selector 轮训，由 EventLoop 负责调度和执行 Selector 轮询操作。
8. 执行网络请求事件通知，轮询准备就绪的 Channel，由 EventLoop 执行 ChannelPipeline。
9. 执行 Netty 系统和业务 ChannelHandler，依次调度并执行 ChannelPipeline 的 ChannelHandler。
10. 通过 Proxy 代理调用后端服务，ChannelRead 事件后，通过发射调度后端 Service。
11. 创建 Session，Session 与 Connection 是相互依赖关系。
12. 创建 Connection，Connection 保存 ChannelHandlerContext。
13. 添加 SessionListener，SessionListener 监听 SessionCreate 和 SessionDestory 等事件。
