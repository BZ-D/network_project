package cn.groupofseven.questionaresystem.controller;

import cn.groupofseven.questionaresystem.sevice.QuestionareService;
import cn.groupofseven.questionaresystem.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/questionare")
public class QuestionareController {
    @Resource
    private QuestionareService questionareService;
    @PostMapping("/release")
    public ResultVO<QuestionareVO> release(@RequestBody QuestionareVO questionare){
        return questionareService.release(questionare);
    }
    @PostMapping("/draft")
    public ResultVO<QuestionareVO> draft(@RequestBody QuestionareVO questionare){
        return questionareService.draft(questionare);
    }
    //api中暂未给出方法，但需要该方法传递用户所有信息
    @PostMapping("/searchByTitle")
    public List<QuestionareVO> searchByTitle(@RequestParam String title){
        return questionareService.searchByTitle(title);
    }
    @PostMapping("/searchByUser/{uid}")
    public List<QuestionareVO> searchByUser(@PathVariable Integer uid){
        return questionareService.searchByUser(uid);
    }
    @PostMapping("/checkFilled/{uid}")
    public List<QuestionareVO> checkFilled(@PathVariable Integer uid){
        return questionareService.checkFilled(uid);
    }
    @PostMapping("/checkReleased/{uid}")
    public List<QuestionareVO> checkReleased(@PathVariable Integer uid){
        return questionareService.checkReleased(uid);
    }
    @PostMapping("/checkDraft/{uid}")
    public List<QuestionareVO> checkDraft(@PathVariable Integer uid){
        return questionareService.checkDraft(uid);
    }

}
