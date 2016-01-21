package com.woods.jdbc.test;

import com.woods.jdbc.Utile.BaseDao;
import com.woods.jdbc.entity.Animals;
import org.junit.Test;

import java.util.List;

/**
 * Created by lin on 2016/1/21.
 */
public class TestBaseDao {

    @Test
    public void test(){

        List<Animals> list = BaseDao.findAll(Animals.class);
        for (Animals animals : list) {
            System.out.println(animals.getId());
        }
    }

    @Test
    public void findById(){
        List<Animals> list = BaseDao.findById(Animals.class, 1);
        for (Animals animals : list) {
            System.out.println("id: " + animals.getTid() + " name: " + animals.getId());
        }
    }

    @Test
    public void save(){
        Animals a = new Animals("花花", 23, 1);
        BaseDao dao = new BaseDao();
        dao.save(a);
    }
}
