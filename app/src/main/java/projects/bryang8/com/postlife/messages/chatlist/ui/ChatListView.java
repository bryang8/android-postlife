package projects.bryang8.com.postlife.messages.chatlist.ui;

import projects.bryang8.com.postlife.entities.User;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface ChatListView {
    void onChatAdded(User user);
    void onContactChanged(User user);
    void onChatRemoved(User user);
}
