package org.homework.questions_bank.service;

import lombok.extern.slf4j.Slf4j;
import org.homework.questions_bank.entity.FilterOption;
import org.homework.questions_bank.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QuestionService {
    List<Question> list();
    public void add(Question question);
    public void delete(int id);

    void update(Question question);
    Question show(int id);

    String answer(int id,String answer);
    double updatePassRate(int qid, boolean isCorrect);

    FilterOption filterOptions();
}
