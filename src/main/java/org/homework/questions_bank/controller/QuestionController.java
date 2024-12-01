package org.homework.questions_bank.controller;


import lombok.extern.slf4j.Slf4j;
import org.homework.questions_bank.entity.AnswerRequest;
import org.homework.questions_bank.entity.FilterOption;
import org.homework.questions_bank.entity.Question;
import org.homework.questions_bank.entity.Result;
import org.homework.questions_bank.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题目管理
 */
@Slf4j
@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/list")
    public Result list()
    {

        log.info("查询所有题目数据");
        List<Question> questionList=questionService.list();
        return Result.success(questionList);
    }
    @PostMapping("/add")
    public Result add(@RequestBody Question question)
    {
        log.info("添加题目");
        log.info(String.valueOf(question));
        questionService.add(question);
        return Result.success();
    }
    @DeleteMapping("/{qid}")
    public Result delete(@PathVariable("qid") Integer id)
    {
        log.info("删除题目");
        questionService.delete(id);
        return Result.success();
    }
    @GetMapping("/{qid}")
    public Result showquestion(@PathVariable("qid") Integer id)
    {
        log.info("展示题目");
        Question question=questionService.show(id);
        return Result.success(question);
    }
    @PostMapping("/answer/{qid}")
    public Result answer(@PathVariable("qid") Integer id, @RequestBody AnswerRequest answerRequest)
    {
        log.info("判断对错");
        return Result.success(questionService.answer(id,answerRequest.getAnswer()));

    }
    @GetMapping("/filter-options")
    public Result filterOptions()
    {
        log.info("根据条件筛选题目");
        FilterOption questionList=questionService.filterOptions();
        return Result.success(questionList);
    }
}