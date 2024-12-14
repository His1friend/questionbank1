package org.homework.questions_bank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.homework.questions_bank.entity.QuestionCombinationRelations;
import org.homework.questions_bank.mapper.QuestionCombinationRelationsMapper;
import org.homework.questions_bank.service.QuestionCombinationRelationsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【question_combination_relations】的数据库操作Service实现
* @createDate 2024-12-14 14:25:21
*/
@Service
public class QuestionCombinationRelationsServiceImpl extends ServiceImpl<QuestionCombinationRelationsMapper, QuestionCombinationRelations>
    implements QuestionCombinationRelationsService {

    @Override
    public List<Integer> getQuestionIdsByCombinationId(Integer combinationId) {
        return baseMapper.getQuestionIdsByCombinationId(combinationId);
    }
}




