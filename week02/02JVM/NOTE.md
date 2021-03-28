## Week02作业
##### 1.（选做）使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。
      在本机已测试演练
      1.1 -XX:UseSerialGC 串行化GC 也叫Minor GC或Young GC ，就是它所谓的DefNew 垃圾回收是单线程，执行效率比较低
          java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseSerialGC GCLogAnalysis
      1.2 -XX:+UseParallelGC Java8默认GC策略，并行GC
          java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseParallelGC GCLogAnalysis
      1.3 -XX:+UseConcMarkSweepGC CMS GC  内存大只会发生 ParNew GC
          java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseConcMarkSweepGC GCLogAnalysis
      1.4 -XX:+UseG1GC G1 GC
          java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx512m -Xms512m -XX:+UseG1GC GCLogAnalysis`
          
2.（选做）使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。
   1. java -jar  -XX:+PrintGC -XX:+PrintGCDateStamps -Xmx512m -Xms512m -XX:+UseG1GC gateway-server-0.0.1-SNAPSHOT.jar
      wrk -c 40 -d30s http://localhost:8088 并发数是17990，Young GC 执行了94次
   2. java -jar  -XX:+PrintGC -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseG1GC gateway-server-0.0.1-SNAPSHOT.jar
      wrk -c 40 -d30s http://localhost:8088 并发数是20082，Young GC 执行了52次
      
4.（必做）根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。
   >总结：
   JVM调优主要看3个方便：内存使用情况、GC运行情况、线程运行情况
   * 7类垃圾收集器：串行GC(Serial GC)、并行GC(Parallel GC)、CMS GC 、G1 GC、ZGC
   * 默认垃圾回收器：Java7和Java8默认是Parallel GC,Java9和Java10默认是G1 GC,Java11默认是ZGC
   * 垃圾回收器和堆内存（使用GCLogAnalysis.java测试）：
   1. Serial收集器：
      串行GC是单线程垃圾回收器，不能进行并行处理，所以会触发全线暂停，停止所有应用线程。
      串行GC算法不能充分利用多核CPU。不管多少CPU内核，JVM垃圾收集时都只能使用单个核心，CPU利用率高、暂停时间长。
   2. Parallel GC:
      重复利用多核CPU的优势，大幅降低GC暂停时间，提升吞吐量。内存越大发生Full GC的频率越低
      例如本机设置堆内存和初始化内存1G发生9次Young GC，设置堆内存和初始化内存512M发生19次Young GC和5次Full GC,之后FullGC 频繁，说明整个堆内存是不够用容易发生内存溢出
      设置堆内存和初始化内存4G发生了2次Young GC，但是暂停时间比之前增大了。内存越大回收频率越低反之。
   3. CMS GC 
      整个操作步骤分为六步：初始标记（initial mark）、并发标记（concurrent mark）、并发预清理（concurrent Preclean）、
                         最终标记（Final Remark）、并发清除（concurrent sweep）、并发重置（concurrent Reset）
      CMS GC并发收集，低停顿，内存越大发生CMS GC频率越小
    4. G1 GC  
       G1收集器时，Java堆的内存布局与其他收集器有很大差别，它将整个Java堆划分为多个大小相等的独立区域（Region），虽然还保留有新生代和老年代的概念，
       但新生代和老年代不再是物理隔阂了，它们都是一部分（可以不连续）Region的集合。
       G1 GC 处理步骤：
       Evacuation Pause:young（纯年轻代模式转移暂停）
       concurrent marking (并发标记)
        阶段 1: initial mark （初始化标记）
        阶段 2: root region scan（Root区扫描）
        阶段 3: concurrent mark（并发标记）
        阶段 4: remark（再次标记）
        阶段 5: cleanup （清理）
        Evacuation Pause (mixed)（转移暂停:混合模式）
        Full GC (Allocation Failure) 
        整个流程跟CMS流程类似，回收是一部分Region,提高收集效率
     引用秦老师：
     选择正确的 GC 算法，唯一可行的方式就是去尝试，一般性的指导原则:
        1. 如果系统考虑吞吐优先，CPU 资源都用来最大程度处理业务，用 Parallel GC; 
        2. 如果系统考虑低延迟有限，每次 GC 时间尽量短，用 CMS GC;
        3. 如果系统内存堆较大，同时希望整体来看平均 GC 时间可控，使用 G1 GC。 
      对于内存大小的考量:
        1. 一般 4G 以上，算是比较大，用 G1 的性价比较高。
        2. 一般超过 8G，比如 16G-64G 内存，非常推荐使用 G1 GC

5.（选做）运行课上的例子，以及 Netty 的例子，分析相关现象。
   wrk -c 40 -d30s http://localhost:8801 并发719 单线程执行性能低
   wrk -c 40 -d30s http://localhost:8802 并发876 多线程执行并发高资源浪费
   wrk -c 40 -d30s http://localhost:8803 并发617 使用线程池来处理节省资源，线程池数增加到8，并发是1114
   
6.（必做）写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801 ，代码提交到 GitHub
   代码路径week01->02JVM 项目 com.geekbang.JVM.OkhttpTest

