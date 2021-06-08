package cn.groupofseven.questionaresystem.qntest;

import cn.groupofseven.questionaresystem.datamapper.QuestionareMapper;
import cn.groupofseven.questionaresystem.datamapper.UserMapper;
import cn.groupofseven.questionaresystem.po.Questionare;
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
    private QuestionareMapper questionareMapper;
    @Test
    public void selectByPrimaryKey(){
        System.out.println("开始测试");
        Questionare u=questionareMapper.selectByPrimaryKey(1);
        System.out.println(u.getTitleOfQn());
    }
}