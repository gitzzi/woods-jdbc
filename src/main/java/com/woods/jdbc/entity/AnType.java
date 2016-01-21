package com.woods.jdbc.entity;

/**
 * Created by lin on 2016/1/21.
 * 动物类别
 */
public class AnType {

    private Integer tid;

    private String tname;

    public AnType() {

    }

    public AnType(Integer tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
