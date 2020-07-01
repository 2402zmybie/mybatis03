package com.hr.test;

import com.hr.dao.IUserDao;
import com.hr.domain.QueryVo;
import com.hr.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MyBatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = sqlSessionFactory.openSession(true);
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws IOException {
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for(User user :users) {
            System.out.println(user);
        }
    }



    @Test
    public void testQueryById() {
        User user = userDao.findById(41);
        System.out.println(user);  //sout
    }


    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%王%");
        System.out.println("模糊查询姓名");
        for(User user : users) {
            System.out.println(user);
        }
    }


    /**
     * 实际开发中应用较多,
     * 开发中通过pojo传递查询条件,查询条件是综合的查询条件,(比如将用户购买的商品信息也作为查询条件)
     * 这时候可以使用包装对象传递输入参数, pojo类中包含pojo
     */
    @Test
    public void testFindUserByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for(User u : users) {
            System.out.println(u);
        }
    }

    // <if></if>  <where></where>
    @Test
    public void testFindUserByCondition() {
        //执行查询只有部分条件的语句
        User u = new User();
        u.setUsername("小二王");
//        u.setSex("女");
        List<User> users = userDao.findUserByCondition(u);
        for(User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试foreach标签的使用,子查询
     */
    @Test
    public void testFindUserInIds() {
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(43);
        vo.setIds(list);
        List<User> users = userDao.findUserInIds(vo);
        for(User user : users) {
            System.out.println(user);
        }
    }

}
