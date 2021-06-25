package cn.groupofseven.questionaresystem.controller;

import cn.groupofseven.questionaresystem.sevice.AnswerService;
import cn.groupofseven.questionaresystem.sevice.UserService;
import cn.groupofseven.questionaresystem.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Resource
    private AnswerService answerService;
    @PostMapping("/insertAnswers")
    public ResultVO<AnswerVO> insertQuestions(@RequestBody AnswerVO answers){
        return answerService.insertAnswers(answers);
    }
    @GetMapping("/getAnswers/{qnId}")
    public List<AnswerVO> getQuestions(@RequestParam Integer uid,@PathVariable Integer qnId){
        return answerService.getAnswers(uid,qnId);
    }
}
