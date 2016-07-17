package projects.bryang8.com.postlife.friends.friendslist;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public interface FriendsInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeFriend(String email);
}
