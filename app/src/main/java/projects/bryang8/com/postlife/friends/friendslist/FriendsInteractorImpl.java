package projects.bryang8.com.postlife.friends.friendslist;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public class FriendsInteractorImpl implements FriendsInteractor {
    private FriendsRepository repository;

    public FriendsInteractorImpl() {
        this.repository = new FriendsRepositoryImpl();
    }

    @Override
    public void subscribe() {
        repository.subscribeForFriendListUpdates();
    }

    @Override
    public void unsubscribe() {
        repository.unSubscribeForFriendListUpdates();
    }

    @Override
    public void destroyListener() {
        repository.destroyFriendListListener();
    }

    @Override
    public void removeFriend(String email) {
        repository.removeFriend(email);
    }
}
