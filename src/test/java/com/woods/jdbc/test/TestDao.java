package com.woods.jdbc.test;

import com.woods.jdbc.Dao.AnimalsDao;
import com.woods.jdbc.Utile.BaseDao;
import com.woods.jdbc.entity.Animals;
import org.junit.Test;

import java.util.List;

/**
 * Created by lin on 2016/1/21.
 */
public class TestDao {

    @Test
    public void test(){
        BaseDao dao  = BaseDao.INSTANCE;
        List<Animals> list = dao.findAll(Animals.class);
        for (Animals animals : list) {
            System.out.println(animals.getId());
        }
    }

    @Test
    public void findById(){
        BaseDao dao  = BaseDao.INSTANCE;
        List<Animals> list = dao.findById(Animals.class, 1);
        for (Animals animals : list) {
            System.out.println("id: " + animals.getTid() + " name: " + animals.getId());
        }
    }

    @Test
    public void save(){
        Animals a = new Animals("花花", 23, 1);
        BaseDao dao  = BaseDao.INSTANCE;
        dao.save(a);
    }

    @Test
    public void update1(){
        AnimalsDao animalsDao = new AnimalsDao();
        Animals a = animalsDao.findById(1);
        System.out.println("更新前name： " + a.getName());;
        a.setName("风风update");
        animalsDao.update1(a);
        a = animalsDao.findById(1);
        System.out.println("更新后name： " + a.getName());;

    }

    @Test
    public void update(){
        AnimalsDao animalsDao = new AnimalsDao();
        Animals a = animalsDao.findById(2);
        System.out.println("更新前name： " + a.getName());;
        a.setName("琪琪update");
        animalsDao.update1(a);
        a = animalsDao.findById(1);
        System.out.println("更新后name： " + a.getName());;

    }
}
