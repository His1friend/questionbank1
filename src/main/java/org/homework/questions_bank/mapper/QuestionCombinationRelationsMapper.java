package org.homework.questions_bank.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.homework.questions_bank.entity.Exams;
import org.homework.questions_bank.entity.QuestionCombinationRelations;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【question_combination_relations】的数据库操作Mapper
* @createDate 2024-12-14 14:25:21
* @Entity src.main.java.org.homework.questions_bank.entity.QuestionCombinationRelations
*/
@Mapper
public interface QuestionCombinationRelationsMapper extends BaseMapper<QuestionCombinationRelations> {
    List<QuestionCombinationRelations> selectList();
    @Insert("INSERT INTO question_combination_relations (combination_id,question_id)\n" +
            "        VALUES (#{combinationId}, #{questionId})")
    int insert(QuestionCombinationRelations record);
    List<Integer> getQuestionIdsByCombinationId(Integer combinationId);

    @Delete("delete from question_combination_relations where combination_id=#{combinationId} and question_id=#{questionId}")
    void deletetheexams(QuestionCombinationRelations questionCombinationRelations);
}




