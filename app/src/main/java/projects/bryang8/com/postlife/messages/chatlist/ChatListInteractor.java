package projects.bryang8.com.postlife.messages.chatlist;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public interface ChatListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeChat(String email);
}
