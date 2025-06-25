package com.example.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class DashscopeRequest {

    private static final String API_URL = "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions";
    private static final String API_KEY = "sk-307c069367ca4e0ba916a6236e84d839"; // 替换为你的API密钥

    public static void main(String[] args) {
        // 构建消息数组
        JSONArray messages = new JSONArray();

        // 添加系统消息
        JSONObject systemMessage = new JSONObject();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a helpful assistant.");
        messages.add(systemMessage);

        // 添加用户消息
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", "你是谁？");
        messages.add(userMessage);

        // 构建请求体
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "qwen-plus");
        requestBody.put("messages", messages);

        // 发送请求
        HttpResponse response = HttpRequest.post(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .execute();

        // 输出响应
        System.out.println("Status: " + response.getStatus());
        System.out.println("Response: " + response.body());
    }
}
