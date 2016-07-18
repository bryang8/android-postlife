package projects.bryang8.com.postlife.messages.chat;

import projects.bryang8.com.postlife.messages.chat.events.ChatEvent;

/**
 * Created by bryan_g8 on 19/06/16.
 */
public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);

    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}
