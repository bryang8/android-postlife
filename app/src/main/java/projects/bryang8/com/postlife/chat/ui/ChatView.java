package projects.bryang8.com.postlife.chat.ui;

import projects.bryang8.com.postlife.entities.ChatMessage;
import projects.bryang8.com.postlife.entities.ChatMessage;

/**
 * Created by bryan_g8 on 19/06/16.
 */
public interface ChatView {
    void sendMessage();
    void onMessageReceived(ChatMessage msg);
}
