package cn.groupofseven.questionaresystem.usertest;

import cn.groupofseven.questionaresystem.datamapper.UserMapper;
import cn.groupofseven.questionaresystem.po.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;
@RunWith(SpringRunner.class)
@SpringBootTest
public class testMapper {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testuserLogin(){
        System.out.println("开始测试");
        User u=userMapper.selectByPrimaryKey(1);
        System.out.println(u.getUname());
    }
}
