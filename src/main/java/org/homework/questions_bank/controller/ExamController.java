package org.homework.questions_bank.controller;

import lombok.extern.slf4j.Slf4j;
import org.homework.questions_bank.entity.Exams;
import org.homework.questions_bank.entity.Question;
import org.homework.questions_bank.entity.QuestionCombinationRelations;
import org.homework.questions_bank.entity.Result;
import org.homework.questions_bank.service.ExamsService;
import org.homework.questions_bank.service.QuestionCombinationRelationsService;
import org.homework.questions_bank.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamsService examsService;

    @Autowired
    private QuestionCombinationRelationsService questionCombinationRelationsService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/getExam")
    public Result getExam() {
        List<Exams> list = examsService.list();
        return Result.success(list);
    }

    @GetMapping("/getQuestion/{examId}")
    public Result getQuestion(@PathVariable Integer examId) {
        List<Integer> listIds = questionCombinationRelationsService.getQuestionIdsByCombinationId(examId);
        List<Question> questions = new ArrayList<>();
        listIds.forEach(questionId -> {
            questions.add(questionService.show(questionId));
        });
        return Result.success(questions);
    }
    @PostMapping("/addExam")
    public Result addExam(@RequestBody Exams exams) {
        examsService.save(exams);
        return Result.success();
    }
    @PostMapping("/addQuestion")
    public Result addQuestion(@RequestBody QuestionCombinationRelations questionCombinationRelations) {
        questionCombinationRelationsService.save(questionCombinationRelations);
        return Result.success();
    }
    @DeleteMapping("/deleteQuestion")
    public Result deleteQuestion(@RequestBody QuestionCombinationRelations questionCombinationRelations) {
        questionCombinationRelationsService.removetheexams(questionCombinationRelations);
        return Result.success();
    }
}
