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
    public ResultVO<QuestionareVO> release(@RequestBody QuestionareVO questionare) {
        return questionareService.release(questionare);
    }

    @PostMapping("/draft")
    public ResultVO<QuestionareVO> draft(@RequestBody QuestionareVO questionare) {
        return questionareService.draft(questionare);
    }

    @GetMapping("/searchByTitle")
    public List<QuestionareVO> searchByTitle(@RequestParam String title) {
        return questionareService.searchByTitle(title);
    }

    @GetMapping("/searchByUser/{uid}")
    public List<QuestionareVO> searchByUser(@PathVariable Integer uid) {
        return questionareService.searchByUser(uid);
    }

    @GetMapping("/checkFilled/{uid}")
    public List<QuestionareVO> checkFilled(@PathVariable Integer uid) {
        return questionareService.checkFilled(uid);
    }

    @GetMapping("/checkReleased/{uid}")
    public List<QuestionareVO> checkReleased(@PathVariable Integer uid) {
        return questionareService.checkReleased(uid);
    }

    @GetMapping("/checkDraft/{uid}")
    public List<QuestionareVO> checkDraft(@PathVariable Integer uid) {
        return questionareService.checkDraft(uid);
    }

}
