package cn.groupofseven.questionaresystem.sevice;

import cn.groupofseven.questionaresystem.vo.AnswerVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AnswerService {
    List<AnswerVO> insertAnswers(List<AnswerVO> answers);

    List<AnswerVO> getAnswers(Integer uid, Integer qnId);
}
