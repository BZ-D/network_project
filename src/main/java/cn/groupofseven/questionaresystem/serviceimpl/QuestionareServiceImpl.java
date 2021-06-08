package cn.groupofseven.questionaresystem.serviceimpl;

import cn.groupofseven.questionaresystem.sevice.QuestionareService;
import cn.groupofseven.questionaresystem.vo.QuestionareVO;
import cn.groupofseven.questionaresystem.vo.ResultVO;

import java.util.List;

public class QuestionareServiceImpl implements QuestionareService {
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
        return null;
    }
}
