package cn.groupofseven.questionaresystem.serviceimpl;

import cn.groupofseven.questionaresystem.datamapper.QuestionMapper;
import cn.groupofseven.questionaresystem.po.Question;
import cn.groupofseven.questionaresystem.sevice.QuestionService;
import cn.groupofseven.questionaresystem.sevice.QuestionareService;
import cn.groupofseven.questionaresystem.vo.QuestionVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public List<QuestionVO> insertQuestions(List<QuestionVO> questions) {
        //遍历问题列表，逐个调用Mapper接口内的插入方法插入数据
        //返回值是传入的参数
        Question questionToInsert;
        for (int i = 0; i < questions.size(); i++) {
            questionToInsert = new Question(questions.get(i));
            if(questionMapper.selectByPrimaryKey(questionToInsert.getId()) != null){

                if (questionMapper.selectByPrimaryKey(questionToInsert.getId()).getQnId().equals(questionToInsert.getQnId())){
                    questionMapper.updateByPrimaryKey(questionToInsert);
                }else {
                    questionMapper.insert(questionToInsert);
                }
            }else {
                questionMapper.insert(questionToInsert);
            }

        }

        return questions;
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
