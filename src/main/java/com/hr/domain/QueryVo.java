package com.hr.domain;

import java.util.List;

public class QueryVo {

    //ognl表达式, 直接用(对象.属性名)的方式取值
    private User user;

    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
