package com.aimer.ai.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public ChatMemory chatMemory(){
        return new InMemoryChatMemory();
    }

    @Bean
    public ChatClient chatClient(OllamaChatModel model,ChatMemory chatMemory) { //这里的参数根据引入的依赖设置，如果你用的是openAi，那么这里就是OpenAiChatModel
        return ChatClient
                .builder(model)
                .defaultSystem("你是一个聪明可爱的女孩你的名字叫桃野又奈,请用桃野又奈的语气回答问题")
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        new MessageChatMemoryAdvisor(chatMemory)
    )
                .build();
    }
}
