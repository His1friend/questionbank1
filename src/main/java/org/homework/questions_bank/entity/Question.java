package org.homework.questions_bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer qid; // 题目ID
    private String questionText; // 题目描述
    private String option; // 选项
    private Integer categoryId; // 题目分类
    private Integer difficultyLevel; // 题目难度
    private Integer passedNumber; // 通过人数
    private Integer totalNumber; // 总人数
    private Integer knowledgeNode; // 知识点
    private String questionName; // 题目名称
    private Timestamp createdAt;      // 创建时间
    private String maker;             // 出题者
    private String answer;

}
