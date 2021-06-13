package cn.groupofseven.questionaresystem.serviceimpl;

import cn.groupofseven.questionaresystem.sevice.QuestionareService;
import cn.groupofseven.questionaresystem.vo.QuestionareVO;
import cn.groupofseven.questionaresystem.vo.ResultVO;

import java.util.List;

public class QuestionareServiceImpl implements QuestionareService {
    
    @Resource
    private QuestionareMapper questionareMapper;
    
    @Override
    public ResultVO<QuestionareVO> release(QuestionareVO questionare) {
        return null;
    }

    @Override
    public ResultVO<QuestionareVO> draft(QuestionareVO questionare) {
        return null;
    }

    @Override
    public List<QuestionareVO> searchByTitle(String title) {
        return null;
    }

    @Override
    public List<QuestionareVO> searchByUser(Integer uid) {
        return null;
    }

    @Override
    public List<QuestionareVO> checkFilled(Integer uid) {
        return null;
    }

    @Override
    public List<QuestionareVO> checkReleased(Integer uid) {
        return null;
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
