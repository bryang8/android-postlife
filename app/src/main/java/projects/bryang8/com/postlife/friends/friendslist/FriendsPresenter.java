package projects.bryang8.com.postlife.friends.friendslist;

import projects.bryang8.com.postlife.friends.friendslist.events.FriendListEvent;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public interface FriendsPresenter {
    void onCreate();
    void onDestroy();
    void onResume();
    void onPause();

    void removeFriend(String email);
    void onEventMainThread(FriendListEvent event);
}
