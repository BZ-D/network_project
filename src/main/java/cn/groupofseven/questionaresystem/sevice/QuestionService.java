package cn.groupofseven.questionaresystem.sevice;

import cn.groupofseven.questionaresystem.vo.QuestionVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface QuestionService {
    List<QuestionVO> insertQuestions( List<QuestionVO> questions);
    public List<QuestionVO> getQuestions( Integer qnId);
}
