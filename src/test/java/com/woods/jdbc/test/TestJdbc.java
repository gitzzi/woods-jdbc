package com.woods.jdbc.test;

import org.junit.Test;

import java.sql.*;

/**
 * Created by lin on 2016/1/21.
 * 测试jdbc
 */
public class TestJdbc {

    @Test
    public void test(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/woods-jdbc", "root", "123456");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM animals");
            while(rs.next()){
                System.out.println("id: " + rs.getInt(1));
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
