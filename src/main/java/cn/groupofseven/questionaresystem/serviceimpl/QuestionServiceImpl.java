package cn.groupofseven.questionaresystem.serviceimpl;

import cn.groupofseven.questionaresystem.datamapper.QuestionMapper;
import cn.groupofseven.questionaresystem.po.Question;
import cn.groupofseven.questionaresystem.sevice.QuestionService;
import cn.groupofseven.questionaresystem.sevice.QuestionareService;
import cn.groupofseven.questionaresystem.util.Constant;
import cn.groupofseven.questionaresystem.vo.QuestionVO;
import cn.groupofseven.questionaresystem.vo.QuestionareVO;
import cn.groupofseven.questionaresystem.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public ResultVO<QuestionVO> insertQuestions(QuestionVO question) {
        //遍历问题列表，逐个调用Mapper接口内的插入方法插入数据
        //返回值是传入的参数
        Question questionToInsert;
        ArrayList<QuestionVO> questions=new ArrayList<QuestionVO>();
        questions.add(question);
        System.out.println(question.getQuestionTitle());
        for (int i = 0; i < questions.size(); i++) {
            questionToInsert = new Question(questions.get(i));
            if(questionMapper.selectByPrimaryKey(questionToInsert.getId()) != null){

                if (questionMapper.selectByPrimaryKey(questionToInsert.getId()).getQnId().equals(questionToInsert.getQnId())){
                    questionMapper.updateByPrimaryKey(questionToInsert);
                }else {
                    questionMapper.insert(questionToInsert);
                }
            }else {
                questionToInsert.setCreateTime(new Date());
                System.out.println(questionToInsert.getQuestionTitle());
                questionMapper.insert(questionToInsert);
            }

        }

        return new ResultVO<QuestionVO>(Constant.REQUEST_SUCCESS,"发布题目成功",questions.get(0));
    }

    @Override
    public List<QuestionVO> getQuestions(Integer qnId) {
        List<Question> questionsByQnId = questionMapper.getQuestionsByQnId(qnId);
        List<QuestionVO> questionVOList = new ArrayList<>();
        for (int i = 0; i < questionsByQnId.size(); i++) {
            questionVOList.add(new QuestionVO(questionsByQnId.get(i)));
        }
        return questionVOList;
    }
}
