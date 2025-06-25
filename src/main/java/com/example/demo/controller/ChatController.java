package com.example.demo.controller;

import com.example.demo.invoke.HutoolAiInvoke;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@Tag(name = "Chat API", description = "通义千问聊天接口")
public class ChatController {

    @Operation(summary = "发送聊天消息", description = "向AI发送消息并获取回复")
    @PostMapping("/completions")
    public ResponseEntity<String> chatCompletion(@RequestBody Map<String, String> request) {
        try {
            // 这里可以添加业务逻辑处理
            String response = HutoolAiInvoke.callWithHutool();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("调用AI服务失败: " + e.getMessage());
        }
    }
}
