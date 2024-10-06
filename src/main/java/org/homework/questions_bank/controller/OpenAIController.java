package org.homework.questions_bank.controller;


import org.homework.questions_bank.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {
    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/ask")
    public String askOpenAI(@RequestBody String userInput) {
        return openAIService.getResponse(userInput);
    }
}
