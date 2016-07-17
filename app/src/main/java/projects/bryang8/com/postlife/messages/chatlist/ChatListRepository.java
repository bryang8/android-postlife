package projects.bryang8.com.postlife.messages.chatlist;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public interface ChatListRepository {
    void removeChat(String email);
    void destroyChatListListener();
    void subscribeForChatListUpdates();
    void unSubscribeForChatListUpdates();
}
