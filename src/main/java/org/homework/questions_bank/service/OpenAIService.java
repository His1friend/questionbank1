package org.homework.questions_bank.service;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
@Slf4j
@Service
public class OpenAIService {


    private String apiKey = "ynERzkiMpbiCQxPYuGtY:IdmBIBXwqVTEGhHYEyhD";
    private static final String BASE_URL = "https://spark-api-open.xf-yun.com/v1/chat/completions";

    public String getResponse(String userInput) {
        // 构造请求体
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "general");
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", userInput.replace("\"", ""));

        requestBody.put("messages", new JSONArray().put(message));

        // 日志输出请求体
        log.info("Request Body: " + requestBody);

        // 发送 POST 请求
        HttpResponse<JsonNode> response = Unirest.post(BASE_URL)
                .header("Authorization", "Bearer " + apiKey)
                .body(requestBody)
                .asJson();

        // 获取状态码
        int statusCode = response.getStatus();
        log.info("Response Status Code: " + statusCode);

        // 处理响应
        if (statusCode == 200) {
            if (response.getBody() != null) {
                // 提取响应中的 content
                String content = response.getBody().getObject() // 获取 JsonNode 对象
                        .getJSONArray("choices") // 选择 "choices" 数组
                        .getJSONObject(0) // 选择数组中的第一个对象
                        .getJSONObject("message") // 获取 "message" 对象
                        .getString("content"); // 获取 "content" 字段

                log.info("Response content: " + content); // 日志输出 content
                return content; // 返回 content
            } else {
                log.error("Response Body is null");
                return "Error: Response Body is null";
            }
        }
         else {
            String errorMessage = "Error: " + response.getStatusText() + " (Status Code: " + statusCode + ")";
            log.error(errorMessage); // 日志输出错误信息
            return errorMessage;
        }
    }
}
