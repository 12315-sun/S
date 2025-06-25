package com.example.demo.invoke;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.core.lang.Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用Hutool实现DashScope API调用
 */
public class HutoolAiInvoke {

    private static final String API_URL = "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions";

    /**
     * 使用Hutool调用通义千问API
     * @return 返回API响应的JSON字符串
     */
    public static String callWithHutool() {
        // 构建消息列表
        List<Map<String, String>> messages = new ArrayList<>();

        // 添加系统消息
        Map<String, String> systemMsg = new HashMap<>();
        systemMsg.put("role", "system");
        systemMsg.put("content", "You are a helpful assistant.");
        messages.add(systemMsg);

        // 添加用户消息
        Map<String, String> userMsg = new HashMap<>();
        userMsg.put("role", "user");
        userMsg.put("content", "华侨大学是几本？");
        messages.add(userMsg);

        // 构建请求体
        JSONObject requestBody = JSONUtil.createObj()
                .set("model", "qwen-plus")
                .set("messages", messages);

        // 发送HTTP请求
        HttpResponse response = HttpRequest.post(API_URL)
                .header("Authorization", "Bearer " + TestApiKey.API_KEY)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .execute();

        // 返回响应内容
        return response.body();
    }

    public static void main(String[] args) {
        try {
            String result = callWithHutool();
            Console.log("API响应结果：\n{}", result);

            // 解析JSON响应（可选）
            JSONObject jsonResult = JSONUtil.parseObj(result);
            if (jsonResult.containsKey("choices") && !jsonResult.getJSONArray("choices").isEmpty()) {
                String content = jsonResult.getJSONArray("choices").getJSONObject(0)
                        .getJSONObject("message").getStr("content");
                Console.log("AI回答：\n{}", content);
            }
        } catch (Exception e) {
            Console.error("调用API时发生错误: {}", e.getMessage());
        }
    }
}
