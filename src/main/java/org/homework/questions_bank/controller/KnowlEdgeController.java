package org.homework.questions_bank.controller;

import lombok.extern.slf4j.Slf4j;
import org.homework.questions_bank.entity.KnowledgeEdge;
import org.homework.questions_bank.entity.KnowledgeNode;
import org.homework.questions_bank.entity.Question;
import org.homework.questions_bank.entity.Result;
import org.homework.questions_bank.service.KnowledgeEdgeService;
import org.homework.questions_bank.service.KnowledgeNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/knowledge")
public class KnowlEdgeController {
    @Autowired
    public KnowledgeEdgeService knowledgeEdgeService;
    @Autowired
    public KnowledgeNodeService knowledgeNodeService;
    @GetMapping("/edge")
    public Result listalledge()
    {
        log.info("查询所有知识点数据");
        List<KnowledgeEdge> knowledgeEdgeList=knowledgeEdgeService.list();
        return Result.success(knowledgeEdgeList);
    }
    @GetMapping("/node")
    public Result listallnode()
    {
        log.info("查询所有知识点数据");
        List<KnowledgeNode> knowledgeNodeList=knowledgeNodeService.list();
        return Result.success(knowledgeNodeList);
    }
    @GetMapping
    public Map<String, Object> getKnowledgeNetwork() {
        Map<String, Object> response = new HashMap<>();
        response.put("nodes",knowledgeNodeService.list());
        response.put("edges",knowledgeEdgeService.list());
        return response;
    }
 
}
