package com.aimer.ai.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.Message;


@Data
@NoArgsConstructor
public class MessageVO {
    private String role; // 发送文字的人是用户还是ai
    private String content; // 发送的内容

    public MessageVO(Message message) {
        switch (message.getMessageType()){
            case USER:
                role = "user";
                break;
            case ASSISTANT:
                role = "assistant";
                break;
            default:
                this.role = "";
        }
        this.content = message.getText();
    }
}
