package org.homework.questions_bank.mapper;

import org.apache.ibatis.annotations.*;
import org.homework.questions_bank.entity.Question;

import java.util.List;

@Mapper
public interface QuestionsMapper {
    @Select("select * from questions")
    List<Question> list();
    @Insert("INSERT INTO questions (question_text, `option`, category_id, difficulty_level, passed_number, total_number, KnowledgeNode, question_name, created_at, maker,answer) " +
            "VALUES (#{questionText}, #{option}, #{categoryId}, #{difficultyLevel}, #{passedNumber}, 0, #{knowledgeNode}, #{questionName}, #{createdAt}, #{maker},#{answer})")
    void insert(Question question);
    @Delete("delete from questions where #{id}=qid")
    void delete(int id);
    @Update("update questions SET question_text = #{questionText},option=#{option} WHERE qid = #{qid};")
    void update(Question question);
}
