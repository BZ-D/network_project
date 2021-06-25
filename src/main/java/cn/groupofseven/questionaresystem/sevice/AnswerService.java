package cn.groupofseven.questionaresystem.sevice;

import cn.groupofseven.questionaresystem.vo.AnswerVO;
import cn.groupofseven.questionaresystem.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AnswerService {
    ResultVO<AnswerVO> insertAnswers(AnswerVO answers);

    List<AnswerVO> getAnswers(Integer uid, Integer qnId);
}
