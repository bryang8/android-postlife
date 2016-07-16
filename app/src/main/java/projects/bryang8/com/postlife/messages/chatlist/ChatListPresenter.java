package projects.bryang8.com.postlife.messages.chatlist;

import projects.bryang8.com.postlife.messages.chatlist.events.ChatListEvent;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public interface ChatListPresenter {
    void onCreate();
    void onDestroy();
    void onResume();
    void onPause();

    void removeChat(String email);
    void onEventMainThread(ChatListEvent event);
}
