package com.hr.dao;

import com.hr.domain.QueryVo;
import com.hr.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface IUserDao {

    List<User> findAll();


    User findById(Integer id);

    /**
     * 根据姓名模糊查询
     * @param username
     * @return
     */
    List<User> findByName(String username);


    /**
     * 根据QueryVo中的查询条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入的参数条件查询
     * @param user 查询的条件, 有可能有用户名,有可能有性别,也有可能有地址, 还有可能是都有
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据QueryVo中提供的id集合, 查询用户信息  in(41,42)
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
