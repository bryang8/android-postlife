package projects.bryang8.com.postlife.chat.events;

import projects.bryang8.com.postlife.entities.ChatMessage;

/**
 * Created by bryan_g8 on 19/06/16.
 */
public class ChatEvent {
    ChatMessage message;

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
