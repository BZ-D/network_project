package cn.groupofseven.questionaresystem.serviceimpl;

import cn.groupofseven.questionaresystem.datamapper.AnswerMapper;
import cn.groupofseven.questionaresystem.datamapper.QuestionareMapper;
import cn.groupofseven.questionaresystem.datamapper.UserMapper;
import cn.groupofseven.questionaresystem.po.Answer;
import cn.groupofseven.questionaresystem.po.Questionare;
import cn.groupofseven.questionaresystem.sevice.QuestionareService;
import cn.groupofseven.questionaresystem.util.Constant;
import cn.groupofseven.questionaresystem.vo.QuestionVO;
import cn.groupofseven.questionaresystem.vo.QuestionareVO;
import cn.groupofseven.questionaresystem.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionareServiceImpl implements QuestionareService {
    @Resource
    private QuestionareMapper questionareMapper;
    @Resource
    private AnswerMapper answerMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public ResultVO<QuestionareVO> release(QuestionareVO questionare) {
        boolean isDraft = questionare.getIsDraft();
        questionare.setIsDraft(false);
        Questionare newquestionare = new Questionare(questionare);
        int uid = questionare.getCreateUserId();
        if(isDraft){   //说明是草稿
            questionareMapper.updateByPrimaryKey(newquestionare);
            userMapper.increaseRelease(uid);
            userMapper.decreaseDraft(uid);
        }
        else{
            newquestionare.setCreateTime(new Date());
            questionareMapper.insert(newquestionare);
            userMapper.increaseRelease(uid);
        }
        questionare.setId(questionareMapper.selectByTitle(questionare.getTitleOfQn()).get(0).getId());
        return new ResultVO<QuestionareVO>(Constant.REQUEST_SUCCESS,"发布问卷成功",questionare);
    }

    @Override
    public ResultVO<QuestionareVO> draft(QuestionareVO questionare) {
        questionare.setIsDraft(true);
        Questionare newquestionare = new Questionare(questionare);
        int uid = questionare.getCreateUserId();
        newquestionare.setCreateTime(new Date());
        questionareMapper.insert(newquestionare);
        userMapper.increaseDraft(uid);
        return new ResultVO<QuestionareVO>(Constant.REQUEST_SUCCESS,"保存草稿成功",questionare);
    }

    @Override
    public List<QuestionareVO> searchByTitle(String title) {
        List<QuestionareVO> VOlist = new ArrayList<>();
        List<Questionare> qnlist = questionareMapper.selectByTitle(title);
        for(Questionare questionare: qnlist){
            VOlist.add(new QuestionareVO(questionare));
        }
        return VOlist;
    }

    @Override
    public List<QuestionareVO> searchByUser(Integer uid) {
        List<QuestionareVO> VOlist = new ArrayList<>();
        List<Questionare> qnlist = questionareMapper.selectByUid(uid);
        for(Questionare questionare: qnlist){
            VOlist.add(new QuestionareVO(questionare));
        }
        return VOlist;
    }

    @Override
    public List<QuestionareVO> checkFilled(Integer uid) {
        //需要返回的List
        List<QuestionareVO> questionareVoList = new ArrayList<QuestionareVO>();
        //从数据库返回的数据
        List<Answer> answerList = answerMapper.selectByUid(uid);
        //遍历从数据库得到的列表，构造返回的列表，QuestionareVO构造函数需要Questionare作为参数
        for(Answer answer:answerList){
            int qnId = answer.getAnswerQnId();
            //判断是不是重复问卷
            boolean isRepeated = false;
            for(QuestionareVO questionareVO:questionareVoList){
                if(questionareVO.getId()==qnId){
                    isRepeated=true;
                    break;
                }
            }
            //如果不是重复问卷，就添加到列表中
            if(!isRepeated) {
                Questionare questionare = questionareMapper.selectByPrimaryKey(qnId);
                QuestionareVO questionareVO = new QuestionareVO(questionare);
                questionareVoList.add(questionareVO);
            }
        }
        return questionareVoList;
    }

    @Override
    public List<QuestionareVO> checkReleased(Integer uid) {
        //需要返回的List
        List<QuestionareVO> questionareVoList = new ArrayList<QuestionareVO>();
        //从数据库返回的数据
        List<Questionare> questionareList =  questionareMapper.selectByUid(uid);
        //遍历从数据库得到的列表，构造返回的列表，QuestionareVO构造函数需要Questionare作为参数
        for(Questionare questionare:questionareList){
            //判断是不是草稿，不是草稿就是发布的
            if(questionare.getIsDraft().equals(false)) {
                QuestionareVO questionareVO = new QuestionareVO(questionare);
                questionareVoList.add(questionareVO);
            }
        }
        return questionareVoList;
    }

    @Override
    public List<QuestionareVO> checkDraft(Integer uid) {
        //从数据库返回的数据
        ArrayList<Questionare> draftQuestionares = questionareMapper.selectDraftByUserId(uid);

        //需要返回的List
        List<QuestionareVO> draftInfomation = new ArrayList<QuestionareVO>();

        //遍历从数据库得到的列表，构造返回的列表，QuestionareVO构造函数需要Questionare作为参数
        for (int i = 0;i<draftQuestionares.size();i++){
            draftInfomation.add(new QuestionareVO(draftQuestionares.get(i)));
        }

        return draftInfomation;
    }
}
