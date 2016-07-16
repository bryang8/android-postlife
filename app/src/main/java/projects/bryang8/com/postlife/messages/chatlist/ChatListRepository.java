package projects.bryang8.com.postlife.messages.chatlist;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public interface ChatListRepository {
    void removeContact(String email);
    void destroyContactListListener();
    void subscribeForContactListUpdates();
    void unSubscribeForContactListUpdates();
    void changeUserConnectionStatus(boolean online);
}
