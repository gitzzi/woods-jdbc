package com.woods.jdbc.entity;

/**
 * Created by lin on 2016/1/21.
 * 动物类
 */
public class Animals {

    private Integer id;

    private String name;

    private Integer age;

    private Integer tid;

    public Animals() {

    }

    public Animals(String name, Integer age, Integer tid) {
        this.name = name;
        this.age = age;
        this.tid = tid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }


}
