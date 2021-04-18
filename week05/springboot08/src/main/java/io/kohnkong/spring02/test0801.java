package io.kohnkong.spring02;

import java.sql.*;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 20:45
 */
public class test0801 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
//        String url = "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=GMT%2B8";
      String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useUnicode=true&useSSL=false";
        Connection con = DriverManager.getConnection(url, "root", "root");
        Statement statement = con.createStatement();
        System.out.println(111);
        ResultSet resultSet = statement.executeQuery("select * from order_info");
        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
        }
    }
}
