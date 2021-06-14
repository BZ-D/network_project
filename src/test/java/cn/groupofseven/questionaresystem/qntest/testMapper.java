package cn.groupofseven.questionaresystem.qntest;

import cn.groupofseven.questionaresystem.datamapper.AnswerMapper;
import cn.groupofseven.questionaresystem.datamapper.QuestionareMapper;
import cn.groupofseven.questionaresystem.datamapper.UserMapper;
import cn.groupofseven.questionaresystem.po.Answer;
import cn.groupofseven.questionaresystem.po.Questionare;
import cn.groupofseven.questionaresystem.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testMapper {
    @Autowired
    private QuestionareMapper questionareMapper;
    @Autowired
    private AnswerMapper answerMapper;
    @Test
    public void selectByPrimaryKey(){
        System.out.println("开始测试");
        Questionare u=questionareMapper.selectByPrimaryKey(1);
        System.out.println(u.getTitleOfQn());
    }
    @Test
    public void testSelectDraft(){
        System.out.println("开始测试");
        ArrayList<Questionare> questionares = questionareMapper.selectDraftByUserId(1);
        for (int i = 0; i < questionares.size(); i++) {
            System.out.println(questionares.get(i).getTitleOfQn());
        }
    }

    @Test
    public void testSelectByTitle(){
        System.out.println("开始测试");
        //ly创建的测试用例，这个用例对数据库的更新会在测试后撤销
        Questionare questionare = new Questionare();
        questionare.setTitleOfQn("调查网站用户基本信息");
        questionare.setIsDraft(false);
        questionare.setNumOfQuestions(5);
        questionare.setId(100);
        questionare.setCreateUserId(2);
        questionare.setCreateTime(new Date());
        questionareMapper.insert(questionare);
        for(Questionare temp : questionareMapper.selectByTitle("调查网站用户基本信息")){
            System.out.print(temp.getId() + " ");
            System.out.println(temp.getNumOfQuestions());
        }
        //撤销对于数据库的更新
        questionareMapper.deleteByPrimaryKey(100);
    }

    @Test
    public void selectByTitle(){
        //
        System.out.println("开始测试");
        Questionare questionare = new Questionare();
        questionare.setTitleOfQn("调查网站用户基本信息");
        questionare.setIsDraft(false);
        questionare.setNumOfQuestions(5);
        questionare.setId(100);
        questionare.setCreateUserId(2);
        questionare.setCreateTime(new Date());
        questionareMapper.insert(questionare);
        for(Questionare temp : questionareMapper.selectByTitle("调查网站用户基本信息")){
            System.out.print(temp.getId() + " ");
            System.out.println(temp.getNumOfQuestions());
        }
        //撤销对于数据库的更新
        questionareMapper.deleteByPrimaryKey(100);
    }


    @Test
    public void testSelectByUid(){
        System.out.println("开始测试");
        //ly创建的测试用例，这个用例对数据库的更新会在测试后撤销
        Questionare questionare = new Questionare();
        questionare.setTitleOfQn("ly创建的测试");
        questionare.setIsDraft(false);
        questionare.setNumOfQuestions(5);
        questionare.setId(2);
        questionare.setCreateUserId(1);
        questionare.setCreateTime(new Date());
        questionareMapper.insert(questionare);
        for(Questionare temp : questionareMapper.selectByUid(1)){
            System.out.print(temp.getId() + " ");
            System.out.println(temp.getNumOfQuestions());
        }
        //撤销对于数据库的更新
        questionareMapper.deleteByPrimaryKey(2);
    }

    @Test
    public void selectByUid(){
        System.out.println("开始测试");
        Questionare questionare = new Questionare();
        questionare.setTitleOfQn("调查网站用户基本信息");
        questionare.setIsDraft(false);
        questionare.setNumOfQuestions(5);
        questionare.setId(100);
        questionare.setCreateUserId(2);
    }

    @Test
    public void selectByUid2(){
        System.out.println("开始测试");
        Answer answer1 = new Answer();
        answer1.setId(9);
        answer1.setAnswerUserId(1);
        answer1.setAnswerQnId(1);
        answer1.setQuestionId(1);
        answer1.setGapAnswer("答案");
        answer1.setSingleAnswer(0);
        answer1.setMultiOne(false);
        answer1.setMultiTwo(false);
        answer1.setMultiThree(false);
        answer1.setMultiFour(false);
        answer1.setMultiFive(false);
        answer1.setMultiSix(false);
        answer1.setCreateTime(new Date());
        answerMapper.insert(answer1);
        Answer answer2 = new Answer();
        answer2.setId(10);
        answer2.setAnswerUserId(1);
        answer2.setAnswerQnId(1);
        answer2.setGapAnswer("答案2");
        answer2.setQuestionId(1);
        answer2.setSingleAnswer(0);
        answer2.setMultiOne(false);
        answer2.setMultiTwo(false);
        answer2.setMultiThree(false);
        answer2.setMultiFour(false);
        answer2.setMultiFive(false);
        answer2.setMultiSix(false);
        answer2.setCreateTime(new Date());
        answerMapper.insert(answer2);
        ArrayList<Answer> answers = answerMapper.selectByUid(1);
        for(Answer answer:answers){
            System.out.print(answer.getId());
        }
        answerMapper.deleteByPrimaryKey(9);
        answerMapper.deleteByPrimaryKey(10);
    }
}