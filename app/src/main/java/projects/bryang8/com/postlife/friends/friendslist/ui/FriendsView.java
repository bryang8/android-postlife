package projects.bryang8.com.postlife.friends.friendslist.ui;

import projects.bryang8.com.postlife.entities.User;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public interface FriendsView {
    void onFriendAdded(User user);
    void onFriendChanged(User user);
    void onFriendRemoved(User user);
}
