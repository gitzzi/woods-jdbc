package com.woods.jdbc.Utile;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016/1/21.
 */
public class BaseDao {

    /**
     * 通用查询方法
     * @param cl
     * @return
     */
    public static List findAll(Class cl){
        List list = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/woods-jdbc", "root", "123456");
//            Statement state = conn.createStatement();
            PreparedStatement ps = null;
            String sql = "SELECT * FROM " + cl.getSimpleName();
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Field[] fields = cl.getDeclaredFields();
            list = new ArrayList();
            while(rs.next()) {
                Object ob = cl.newInstance();
                for (Field ff : fields){
                    ff.setAccessible(true);
                    ff.set(ob, rs.getObject(ff.getName()));
                }
                list.add(ob);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 通过id查找对象
     * @param cl
     * @param id
     * @return
     */
    public static List findById(Class cl, Integer id){
        List list = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/woods-jdbc", "root", "123456");
            PreparedStatement ps = null;
            Field[] fields = cl.getDeclaredFields();
            String sql = "SELECT * FROM " + cl.getSimpleName() + " WHERE " + fields[0].getName() + " = " + id;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            list = new ArrayList();
            while (rs.next()){
                Object ob = cl.newInstance();
                for (Field ff : fields) {
                    ff.setAccessible(true);
                    ff.set(ob, rs.getObject(ff.getName()));
                }
                list.add(ob);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 通用保存方法
     * @param obj
     * @return
     */
    public boolean save(Object obj){
        boolean flag = false;
        Connection conn = BaseConnection.getConnection();
        PreparedStatement ps = null;
        Field[] fields = obj.getClass().getDeclaredFields();
        StringBuffer sb = new StringBuffer();

        StringBuffer fieldNames = new StringBuffer();
        StringBuffer values = new StringBuffer();
        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            fieldNames.append(fields[i].getName());
            values.append(" ? ");
            if(i < fields.length-1) {
                fieldNames.append(" , ");
                values.append(" , ");
            }
        }

        sb.append("insert into " + obj.getClass().getSimpleName() + " ( ");
        sb.append(fieldNames + " ) values ( " + values + " ) ");

        try {
            ps = conn.prepareStatement(sb.toString());
            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                ps.setObject(i, fields[i].get(obj));
            }
            int i = ps.executeUpdate();
            if(i > 0){
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            BaseConnection.closeRes(conn, ps);
        }
        System.out.println(flag);
        return flag;
    }


}
