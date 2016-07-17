package projects.bryang8.com.postlife.friends.friendslist;

import org.greenrobot.eventbus.Subscribe;

import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.friends.friendslist.events.FriendListEvent;
import projects.bryang8.com.postlife.friends.friendslist.ui.FriendsView;
import projects.bryang8.com.postlife.lib.EventBus;
import projects.bryang8.com.postlife.lib.GreenRobotEventBus;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public class FriendsPresenterImpl implements FriendsPresenter {
    private EventBus eventBus;
    private FriendsView view;
    private FriendsInteractor interactor;

    public FriendsPresenterImpl(FriendsView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new FriendsInteractorImpl();
    }

    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        interactor.destroyListener();
        view = null;
    }

    @Override
    public void onPause() {
        interactor.unsubscribe();
    }

    @Override
    public void onResume() {
        interactor.subscribe();
    }

    @Override
    public void removeFriend(String email) {
        interactor.removeFriend(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(FriendListEvent event) {
        User user = event.getUser();
        switch (event.getEventType()) {
            case FriendListEvent.onFriendAdded:
                onChatAdded(user);
                break;
            case FriendListEvent.onFriendChanged:
                onContactChanged(user);
                break;
            case FriendListEvent.onFriendRemoved:
                onChatRemoved(user);
                break;
        }
    }

    public void onChatAdded(User user) {
        if (view != null) {
            view.onFriendAdded(user);
        }
    }

    public void onContactChanged(User user) {
        if (view != null) {
            view.onFriendChanged(user);
        }
    }

    public void onChatRemoved(User user) {
        if (view != null) {
            view.onFriendRemoved(user);
        }
    }
}
