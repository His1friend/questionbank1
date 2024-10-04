package org.homework.questions_bank.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.homework.questions_bank.entity.Question;
import org.homework.questions_bank.mapper.QuestionsMapper;
import org.homework.questions_bank.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionsMapper questionsMapper;
    @Override
    public List<Question> list() {
        List<Question> deptList = questionsMapper.list();
        return deptList;
    }
    @Override
    public void add(Question question)
    {
        question.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        questionsMapper.insert(question);
    }

    @Override
    public void delete(int id) {
        questionsMapper.delete(id);
    }

}
