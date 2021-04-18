### 第5周作业
##### 2. 写代码实现Spring bean的装配，方式越多越好（XML、Annotation 都可以）
     * 第一种方式 基于XML装配 beans1.xml  main方法 XmlBeanAssembleTest 
       1. 设值注入
       2. 构造函数注入
     * 第二种方式 基于Annotation装配 beans2.xml  main方法 AnnotationAssembleTest
     
##### 8.给前面课程提供的Student/Klass/School 实现自动配置和Starter
     * 路径： week05/spring01/src/main/java/io/kohnkong/title08/Test.java
      备注：感觉做的不理想，请老师指正，谢谢，或者找个参考代码
#### 10.研究一下JDBX接口和数据库连接池，掌握它们的设计和用法
     * 1）路径 ：week05/springboot08/src/main/java/io/kohnkong/spring02/JDBCUtil.java
          execute();查询
          insert(); 增加
          update(); 修改
          delete(); 删除
      * 2）insert(); 增加+事务
      * 3）executePool();Hikai 连接池