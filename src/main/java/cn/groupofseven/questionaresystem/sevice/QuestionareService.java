package cn.groupofseven.questionaresystem.sevice;

import cn.groupofseven.questionaresystem.vo.QuestionareVO;
import cn.groupofseven.questionaresystem.vo.ResultVO;

import java.util.List;

public interface QuestionareService {
    ResultVO<QuestionareVO> release(QuestionareVO questionare);
    ResultVO<QuestionareVO> draft(QuestionareVO questionare);
    List<QuestionareVO> searchByTitle (String title);
    List<QuestionareVO> searchByUser (Integer uid);
    List<QuestionareVO> checkFilled (Integer uid);
    List<QuestionareVO> checkReleased (Integer uid);
    List<QuestionareVO> checkDraft (Integer uid);
}
