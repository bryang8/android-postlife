package projects.bryang8.com.postlife.messages.addchat;


import projects.bryang8.com.postlife.messages.addchat.events.AddChatEvent;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface AddChatPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddChatEvent event);
}
