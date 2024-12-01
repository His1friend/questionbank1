package org.homework.questions_bank.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.homework.questions_bank.entity.FilterOption;
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
    @Override
    public void update(Question question){
        questionsMapper.update(question);

    }
    @Override
    public Question show(int id)
    {
        return questionsMapper.show(id);
    }
    @Override
    public String answer(int id,String answer)
    {
        Question question=questionsMapper.show(id);
        log.info(answer);
        log.info(question.getAnswer());
        if (question != null) {
            // 对比提交的答案和正确答案
            if(question.getCategoryId()==1&&question.getAnswer().equals(answer))
            {
                updatePassRate(id,true);
                    return "Answer is correct!";
            }
                else{
                updatePassRate(id,false);
                    return "Answer is incorrect!";

            }
        }
        return "wrong";
    }
    public double updatePassRate(int qid, boolean isCorrect) {
        Question question =questionsMapper.show(qid);


        if (question != null) {
            // 更新总回答次数
            question.setTotalNumber(question.getTotalNumber() + 1);

            // 如果答案正确，则增加通过次数
            if (isCorrect) {
                question.setPassedNumber(question.getPassedNumber() + 1);
            }

            // 保存更新后的题目
            questionsMapper.update(question);

            // 返回更新后的通过率
            return question.getPassedNumber();
        }
        return 0; // 如果找不到题目，返回0
    }

    @Override
    public FilterOption filterOptions() {

        return questionsMapper.filterOptions();
    }

}
