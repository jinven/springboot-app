package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MySqlController {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "123456";
	
	@RequestMapping("/mysql")
	public String index() {
        String message;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            String sql = "select * from blog";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id+":"+name);
            }
            rs.close();
            statement.close();
            connection.close();
            message = "MySql连接测试成功";
        } catch (Exception e) {
            message = "MySql连接失败";
            e.printStackTrace();
        }
		return message;
	}
}
