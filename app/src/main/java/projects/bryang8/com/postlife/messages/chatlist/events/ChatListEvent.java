package projects.bryang8.com.postlife.messages.chatlist.events;

import projects.bryang8.com.postlife.entities.User;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public class ChatListEvent {
    private User user;
    private int eventType;

    public final static int onChatAdded = 0;
    public final static int onContactChanged = 1;
    public final static int onChatRemoved = 2;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
