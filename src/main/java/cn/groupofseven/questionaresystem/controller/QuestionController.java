package cn.groupofseven.questionaresystem.controller;

import cn.groupofseven.questionaresystem.sevice.QuestionService;
import cn.groupofseven.questionaresystem.sevice.UserService;
import cn.groupofseven.questionaresystem.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private QuestionService questionService;
    @PostMapping("/insertQuestions/{qnId}")
    public List<QuestionVO> insertQuestions(@RequestBody List<QuestionVO> questions){
        return questionService.insertQuestions(questions);
    }
    @PostMapping("/getQuestions/{qnId}")
    public List<QuestionVO> getQuestions(@PathVariable Integer qnId){
        return questionService.getQuestions(qnId);
    }
}
