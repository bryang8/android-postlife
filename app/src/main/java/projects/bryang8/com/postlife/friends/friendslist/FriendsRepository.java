package projects.bryang8.com.postlife.friends.friendslist;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public interface FriendsRepository {
    void removeFriend(String email);
    void destroyFriendListListener();
    void subscribeForFriendListUpdates();
    void unSubscribeForFriendListUpdates();
}
