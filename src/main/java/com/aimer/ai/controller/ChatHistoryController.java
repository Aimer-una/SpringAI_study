package com.aimer.ai.controller;

import com.aimer.ai.entity.vo.MessageVO;
import com.aimer.ai.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ai/history")
@RequiredArgsConstructor
public class ChatHistoryController {

    private final ChatHistoryRepository chatHistoryRepository;
    private final ChatMemory chatMemory;

    /**
     * 根据会话类型查询会话记录id
     * @param type
     * @return
     */
    @GetMapping("/{type}")
    public List<String> getChatIds(@PathVariable("type") String type){
        return chatHistoryRepository.getChatIds(type);
    }

    /**
     * 查询会话记录详情
     * @param type
     * @param chatId
     * @return
     */
    @GetMapping("/{type}/{chatId}")
    public List<MessageVO> getChatHistory(@PathVariable("type") String type,@PathVariable("chatId") String chatId){
        List<Message> messages = chatMemory.get(chatId, Integer.MAX_VALUE);
        if (messages == null){
            return List.of();
        }
        return messages.stream().map(MessageVO::new).toList();
    }
}
