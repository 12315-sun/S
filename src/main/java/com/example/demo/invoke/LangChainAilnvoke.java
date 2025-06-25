package com.example.demo.invoke;

import dev.langchain4j.community.model.dashscope.QwenChatModel;

public class LangChainAilnvoke{
    public static void main(String[] args){
        QwenChatModel qwenChatModel = QwenChatModel.builder()
                .apiKey(TestApiKey.API_KEY)
                .modelName("qwen-max")
                .build();

        String result = qwenChatModel.chat("你好，我是代码校园");
        System.out.println(result);
    }
}
