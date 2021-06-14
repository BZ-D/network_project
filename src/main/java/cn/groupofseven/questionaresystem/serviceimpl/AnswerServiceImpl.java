package cn.groupofseven.questionaresystem.serviceimpl;

import cn.groupofseven.questionaresystem.datamapper.AnswerMapper;
import cn.groupofseven.questionaresystem.datamapper.QuestionareMapper;
import cn.groupofseven.questionaresystem.datamapper.UserMapper;
import cn.groupofseven.questionaresystem.po.Answer;
import cn.groupofseven.questionaresystem.sevice.AnswerService;
import cn.groupofseven.questionaresystem.vo.AnswerVO;
import cn.groupofseven.questionaresystem.vo.QuestionareVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Resource
    private AnswerMapper answerMapper;


    @Override
    public List<AnswerVO> insertAnswers(List<AnswerVO> answers) {
        //遍历答案列表，逐个调用Mapper接口内的插入方法插入数据
        //返回值是传入的参数
        for (AnswerVO ansVo: answers) {
            Answer answer = new Answer(ansVo);

            if(answerMapper.selectByPrimaryKey(answer.getId()) != null){

                if (answerMapper.selectByPrimaryKey(answer.getId()).getAnswerUserId().equals(answer.getAnswerUserId())
                ||answerMapper.selectByPrimaryKey(answer.getId()).getAnswerQnId().equals(answer.getAnswerQnId())
                ||answerMapper.selectByPrimaryKey(answer.getId()).getQuestionId().equals(answer.getQuestionId())){
                    answerMapper.updateByPrimaryKey(answer);
                }else {
                    answerMapper.insert(answer);
                }
            }else {
                answerMapper.insert(answer);
            }
        }
        return answers;
    }

    @Override
    public List<AnswerVO> getAnswers(Integer uid, Integer qnId) {
        List<AnswerVO> answerVOList = new ArrayList<AnswerVO>();
        List<Answer> answers = answerMapper.selectByUid(uid);
        for(Answer answer:answers){
            if(answer.getAnswerQnId().equals(qnId)){
                AnswerVO answerVO = new AnswerVO(answer);
                answerVOList.add(answerVO);
            }
        }
        return answerVOList;
    }
}
