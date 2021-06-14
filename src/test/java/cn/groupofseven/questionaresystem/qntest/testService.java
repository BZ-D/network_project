package cn.groupofseven.questionaresystem.qntest;

import cn.groupofseven.questionaresystem.datamapper.QuestionareMapper;
import cn.groupofseven.questionaresystem.datamapper.UserMapper;
import cn.groupofseven.questionaresystem.po.Questionare;
import cn.groupofseven.questionaresystem.po.User;
import cn.groupofseven.questionaresystem.serviceimpl.QuestionareServiceImpl;
import cn.groupofseven.questionaresystem.sevice.AnswerService;
import cn.groupofseven.questionaresystem.sevice.QuestionService;
import cn.groupofseven.questionaresystem.sevice.QuestionareService;
import cn.groupofseven.questionaresystem.vo.AnswerVO;
import cn.groupofseven.questionaresystem.vo.QuestionVO;
import cn.groupofseven.questionaresystem.vo.QuestionareVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testService {
    @Resource
    private QuestionareService questionareService;
    @Resource
    private QuestionService questionService;
    @Resource
    private AnswerService answerService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private QuestionareMapper questionareMapper;
    User user;
    QuestionareVO questionareVO;

    @Test
    public void testCheckDraft(){
        List<QuestionareVO> questionareVOList = questionareService.checkDraft(1);
        for (int i = 0; i < questionareVOList.size(); i++) {
            System.out.println(questionareVOList.get(i).getTitleOfQn());
        }
    }

    @Test
    public void testInsertQuestion(){
        Date date = new Date();
        QuestionVO questionVO = new QuestionVO();
        questionVO.setQuestionTitle("你的XX？");
        questionVO.setIsMust(false);
        questionVO.setType("single");
        questionVO.setId(8);
        questionVO.setQnId(1);
        questionVO.setOptionA("dd");
        questionVO.setOptionB("dd");
        questionVO.setOptionC("dd");
        questionVO.setOptionD("dd");
        questionVO.setOptionE("dd");
        questionVO.setOptionF("dd");
        questionVO.setCreateTime(date);
        QuestionVO questionVO1 = new QuestionVO();
        questionVO1.setQuestionTitle("你的XX？");
        questionVO1.setType("single");
        questionVO1.setId(9);
        questionVO1.setQnId(1);
        questionVO1.setCreateTime(date);
        questionVO1.setOptionA("dd");
        questionVO1.setIsMust(false);
        questionVO1.setOptionB("dd");
        questionVO1.setOptionC("dd");
        questionVO1.setOptionD("dd");
        questionVO1.setOptionE("dd");
        questionVO1.setOptionF("dd");
        QuestionVO questionVO2 = new QuestionVO();
        questionVO2.setQuestionTitle("你的XX？");
        questionVO2.setId(10);
        questionVO2.setQnId(1);
        questionVO2.setCreateTime(date);
        questionVO2.setOptionA("dd");
        questionVO2.setOptionB("dd");
        questionVO2.setOptionC("dd");
        questionVO2.setOptionD("dd");
        questionVO2.setOptionE("dd");
        questionVO2.setOptionF("dd");
        questionVO2.setType("single");
        questionVO2.setIsMust(false);
        List<QuestionVO> gg = new ArrayList<>();
        gg.add(questionVO);
        gg.add(questionVO1);
        gg.add(questionVO2);
        questionService.insertQuestions(gg);
    }

    @Test
    public void testGetQuestion(){
        List<QuestionVO> questions = questionService.getQuestions(1);
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i).getQuestionTitle());
        }
    }

    @Test
    public void testReleased(){
        System.out.println("开始测试");
        user = userMapper.selectByPrimaryKey(1);
        questionareVO = new QuestionareVO(questionareMapper.selectByPrimaryKey(1));
        System.out.println(user.getNumOfRelease());
        System.out.println(user.getNumOfDraft());

        questionareService.release(questionareVO);

        questionareVO = new QuestionareVO(questionareMapper.selectByPrimaryKey(1));
        System.out.println(questionareVO.getIsDraft());
        System.out.println(user.getNumOfRelease());
        System.out.println(user.getNumOfDraft());
    }

    @Test
    public void testInsertAnswers(){
        Date date = new Date();
        AnswerVO answerVO = new AnswerVO();
        answerVO.setId(9);
        answerVO.setAnswerUserId(1);
        answerVO.setAnswerQnId(1);
        answerVO.setQuestionId(2);
        answerVO.setGapAnswer("答案");
        answerVO.setSingleAnswer(0);
        answerVO.setMultiOne(false);
        answerVO.setMultiThree(false);
        answerVO.setMultiTwo(false);
        answerVO.setMultiFour(false);
        answerVO.setMultiFive(false);
        answerVO.setMultiSix(false);
        answerVO.setCreateTime(date);

        AnswerVO answerVO1 = new AnswerVO();
        answerVO1.setId(10);
        answerVO1.setAnswerUserId(1);
        answerVO1.setAnswerQnId(1);
        answerVO1.setQuestionId(2);
        answerVO1.setGapAnswer("答案1");
        answerVO1.setSingleAnswer(0);
        answerVO1.setMultiOne(false);
        answerVO1.setMultiThree(false);
        answerVO1.setMultiTwo(false);
        answerVO1.setMultiFour(false);
        answerVO1.setMultiFive(false);
        answerVO1.setMultiSix(false);
        answerVO1.setCreateTime(date);

        AnswerVO answerVO2 = new AnswerVO();
        answerVO2.setId(11);
        answerVO2.setAnswerUserId(1);
        answerVO2.setAnswerQnId(1);
        answerVO2.setQuestionId(1);
        answerVO2.setGapAnswer("答案2");
        answerVO2.setSingleAnswer(0);
        answerVO2.setMultiOne(false);
        answerVO2.setMultiThree(false);
        answerVO2.setMultiTwo(false);
        answerVO2.setMultiFour(false);
        answerVO2.setMultiFive(false);
        answerVO2.setMultiSix(false);
        answerVO2.setCreateTime(date);
        List<AnswerVO> answerVOList = new ArrayList<>();
        answerVOList.add(answerVO);
        answerVOList.add(answerVO1);
        answerVOList.add(answerVO2);
        answerService.insertAnswers(answerVOList);
    }

    @Test
    public void testGetAnswers(){
        List<AnswerVO> answerVOList = answerService.getAnswers(1,1);
        for (int i = 0; i < answerVOList.size(); i++) {
            System.out.println(answerVOList.get(i).getGapAnswer());
        }
    }

    @Test
    public void testCheckReleased(){
        List<QuestionareVO> questionareVOList = questionareService.checkReleased(1);
        for (int i = 0; i < questionareVOList.size(); i++) {
            System.out.println(questionareVOList.get(i).getTitleOfQn());
        }
    }

    @Test
    public void testCheckFilled(){
        List<QuestionareVO> questionareVOList = questionareService.checkFilled(1);
        for (int i = 0; i < questionareVOList.size(); i++) {
            System.out.println(questionareVOList.get(i).getTitleOfQn());
        }
    }

    @Test
    public void testRelease(){
        //测试draft的发布
        System.out.println("测试draft的发布");
        Questionare u = new Questionare();
        u.setId(3);
        u.setNumOfQuestions(5);
        u.setTitleOfQn("ly创建的测试");
        u.setCreateUserId(1);
        u.setIsDraft(true);
        u.setCreateTime(new Date());
        questionareVO = new QuestionareVO(u);
        System.out.println(userMapper.selectByPrimaryKey(1).getNumOfDraft());
        System.out.println(userMapper.selectByPrimaryKey(1).getNumOfRelease());
        //这里测试返回给前端的数值
        System.out.println(questionareService.release(questionareVO).getData().getIsDraft());
        //这里测试对后端数据库的更新
        System.out.println(userMapper.selectByPrimaryKey(1).getNumOfDraft());
        System.out.println(userMapper.selectByPrimaryKey(1).getNumOfRelease());
        //撤销对数据库的更改
        questionareMapper.deleteByPrimaryKey(3);

        //测试非draft的发布
        System.out.println("测试非draft的发布");
        questionareVO.setIsDraft(false);
        System.out.println(userMapper.selectByPrimaryKey(1).getNumOfDraft());
        System.out.println(userMapper.selectByPrimaryKey(1).getNumOfRelease());

        System.out.println(questionareService.release(questionareVO).getData().getIsDraft());

        System.out.println(userMapper.selectByPrimaryKey(1).getNumOfDraft());
        System.out.println(userMapper.selectByPrimaryKey(1).getNumOfRelease());
        //撤销对数据库的更改
        questionareMapper.deleteByPrimaryKey(3);
    }

    @Test
    public void testDraft(){
        System.out.println("开始测试");
        //创建一个测试对象插入数据库，在测试进行后会撤销其对数据库的更改
        Questionare u = new Questionare();
        u.setId(3);
        u.setNumOfQuestions(5);
        u.setTitleOfQn("ly创建的测试");
        u.setCreateUserId(1);
        u.setIsDraft(false);
        u.setCreateTime(new Date());
        System.out.println(userMapper.selectByPrimaryKey(1).getNumOfDraft());
        //这里测试返回给前端的数值
        System.out.println(questionareService.draft(new QuestionareVO(u)).getData().getIsDraft());
        //这里测试对后端数据库的更新
        System.out.println(userMapper.selectByPrimaryKey(1).getNumOfDraft());
        //撤销对数据库的更改
        questionareMapper.deleteByPrimaryKey(3);
    }

    @Test
    public void testSearchByTitle(){
        //ly创建的测试用例，这个用例会在测试后被删除
        System.out.println("开始测试");
        Questionare questionare = new Questionare();
        questionare.setTitleOfQn("调查网站用户基本信息");
        questionare.setIsDraft(false);
        questionare.setNumOfQuestions(5);
        questionare.setId(100);
        questionare.setCreateUserId(2);
        questionare.setCreateTime(new Date());
        questionareMapper.insert(questionare);
        for(QuestionareVO temp : questionareService.searchByTitle("调查网站用户基本信息")){
            System.out.print(temp.getId() + " ");
            System.out.println(temp.getNumOfQuestions());
        }
        //撤销对于数据库的更新
        questionareMapper.deleteByPrimaryKey(100);
    }

    @Test
    public void testSearchByUser(){
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
        for(QuestionareVO temp : questionareService.searchByUser(1)){
            System.out.print(temp.getId() + " ");
            System.out.println(temp.getNumOfQuestions());
        }
        //撤销对于数据库的更新
        questionareMapper.deleteByPrimaryKey(2);
    }
}

