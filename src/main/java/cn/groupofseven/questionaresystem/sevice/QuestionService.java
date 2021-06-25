package cn.groupofseven.questionaresystem.sevice;

import cn.groupofseven.questionaresystem.vo.QuestionVO;
import cn.groupofseven.questionaresystem.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface QuestionService {
    ResultVO<QuestionVO> insertQuestions(QuestionVO question);
    public List<QuestionVO> getQuestions( Integer qnId);
}
