package io.kohnkong.spring02;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.*;
import java.util.Optional;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 21:07
 */
@Slf4j
@EnableTransactionManagement
public class JDBCUtil {
    private static void execute() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useUnicode=true&useSSL=false", "root", "root");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from order_info");
        ResultSet resultSet = preparedStatement.executeQuery();
        OrderInfo orderInfo = null;
        while (resultSet.next()) {
            int orderNo = resultSet.getInt("order_no");
            String name = resultSet.getString("name");
            orderInfo = new OrderInfo().setOrderInfo(orderNo).setName(name);
        }
        boolean present = Optional.ofNullable(orderInfo).isPresent();
        if (present) {
            System.out.println(orderInfo);
        } else {
            System.out.println("查询内容为空");
        }
    }

    private static void executePool() throws ClassNotFoundException, SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useUnicode=true&useSSL=false");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setPoolName("MyHikari");
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(180000);
        hikariConfig.setAutoCommit(true);
        hikariConfig.setConnectionTimeout(30000);
        hikariConfig.setMaxLifetime(1800000);
        hikariConfig.setConnectionTestQuery("select 1");
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        Connection connection = hikariDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from order_info");
        ResultSet resultSet = preparedStatement.executeQuery();
        OrderInfo orderInfo = null;
        while (resultSet.next()) {
            int orderNo = resultSet.getInt("order_no");
            String name = resultSet.getString("name");
            orderInfo = new OrderInfo().setOrderInfo(orderNo).setName(name);
            System.out.println(orderInfo);
        }
        boolean present = Optional.ofNullable(orderInfo).isPresent();
        if (!present) {
            System.out.println("查询内容为空");
        }
    }

    private static void insert() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useUnicode=true&useSSL=false", "root", "root");

            // 定义事务的起点
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("insert into order_info values(?,?)");
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "100003");
            boolean flag = preparedStatement.execute();
//            int temp = 1 / 0;// 模拟抛出异常，导致回滚，所以上面的执行也回滚了
            // 如果全部执行成功，则提交事务
            connection.commit();
            String message = !flag ? "成功插入一条数据" : "保存数据失败";
            log.info("message:{}", message);
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {// 如果出现异常则回滚
                ex.printStackTrace();
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static void delete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useUnicode=true&useSSL=false", "root", "root");
        PreparedStatement preparedStatement = connection.prepareStatement("delete from order_info where order_no =?");
        preparedStatement.setInt(1, 2);
        int count = preparedStatement.executeUpdate();
        String message = count > 0 ? "删除数据库的数据成功" : "删除数据库的数据失败";
        log.info("message:{}", message);
    }

    private static void update() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useUnicode=true&useSSL=false", "root", "root");
        PreparedStatement preparedStatement = connection.prepareStatement("update order_info set name =? where order_no= ?");
        preparedStatement.setString(1, "100003");
        preparedStatement.setInt(2, 1);
        int count = preparedStatement.executeUpdate();
        String message = count > 0 ? "更新数据库数据成功" : "更新数据库数据失败";
        log.info("message:{}", message);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 查询
        //execute();
        // 增加 实物
        //  insert();
        // 修改
        //update();
        // 删除
       // delete();

        //Hikai 连接池
        executePool();
    }
}
