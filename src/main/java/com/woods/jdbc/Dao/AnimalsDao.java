package com.woods.jdbc.Dao;

import com.woods.jdbc.Utile.BaseConnection;
import com.woods.jdbc.Utile.BaseDao;
import com.woods.jdbc.entity.Animals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016/1/21.
 */
public class AnimalsDao{

    /**
     * 普通查询全部
     * @return
     */
    public List<Animals> findAll1(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Animals> animalsList = new ArrayList<Animals>();
        conn = BaseConnection.getConnection();
        String sql = "SELECT * FROM animals";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Animals a = new Animals();
                a.setId(rs.getInt(1));
                a.setName(rs.getString(2));
                a.setAge(rs.getInt(3));
                a.setTid(rs.getInt(4));
                animalsList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            BaseConnection.closeRes(conn, ps, rs);
        }
        return animalsList;
    }

    /**
     * 通用查询全部
     * @return
     */
    public List<Animals> findAll(){
        BaseDao dao = BaseDao.INSTANCE;
        List<Animals> animalsList = new ArrayList<Animals>();
        animalsList = dao.findAll(Animals.class);
        return animalsList;
    }

    /**
     * 普通保存方法
     * @return
     */
    public boolean save1(Animals animals){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        conn = BaseConnection.getConnection();
        String sql = "INSERT INTO ANIMLAS(NAME, AGE, Tid) VALUES (?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, animals.getName());
            ps.setInt(2, animals.getAge());
            ps.setInt(3, animals.getTid());
            int i = ps.executeUpdate();
            flag = i > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            BaseConnection.closeRes(conn, ps);
        }
        return flag;
    }

    /**
     * 通用查找，通过id
     */
    public Animals findById(Integer id){
        List<Animals> as = null;
        if(id != null) {
            BaseDao dao = BaseDao.INSTANCE;
            as = dao.findById(Animals.class, id);
        }
        return as.size() > 0 ? as.get(0) : null;
    }

    /**
     * 通用保存方法
     */
    public boolean save(Animals animals){
        BaseDao dao = BaseDao.INSTANCE;
        return dao.save(animals);
    }

    /**
     * 普通更新方法
     */
    public boolean update1(Animals animals){
        boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;

        conn = BaseConnection.getConnection();
        String sql = "UPDATE ANIMALS SET NAME = ?, AGE = ?, TID = ? WHERE ID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, animals.getName());
            ps.setInt(2, animals.getAge());
            ps.setInt(3, animals.getTid());
            ps.setInt(4, animals.getId());
            int i = ps.executeUpdate();
            flag = i > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 通用更新方法
     */
    public boolean update(Animals animals){
        BaseDao dao = BaseDao.INSTANCE;
        return dao.update(animals);
    }

}
