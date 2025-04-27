package com.aimer.ai.controller;

import com.aimer.ai.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class ChatController{
    private final ChatClient chatClient;
    private final ChatHistoryRepository chatHistoryRepository;
    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> chat(String prompt, String chatId){
        // 保存对话id
        chatHistoryRepository.save("chat",chatId);
        return chatClient.prompt()
                .user(prompt)
                .advisors(a->a.param(CHAT_MEMORY_CONVERSATION_ID_KEY,chatId))
                .stream()
                .content();
    }
}
