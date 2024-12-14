package org.homework.questions_bank.service;

import org.homework.questions_bank.entity.QuestionCombinationRelations;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【question_combination_relations】的数据库操作Service
* @createDate 2024-12-14 14:25:21
*/
public interface QuestionCombinationRelationsService extends IService<QuestionCombinationRelations> {
    List<Integer> getQuestionIdsByCombinationId(Integer combinationId);
}
