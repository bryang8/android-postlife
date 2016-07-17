package projects.bryang8.com.postlife.friends.friendslist;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import projects.bryang8.com.postlife.domain.FirebaseHelper;
import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.friends.friendslist.events.FriendListEvent;
import projects.bryang8.com.postlife.lib.EventBus;
import projects.bryang8.com.postlife.lib.GreenRobotEventBus;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public class FriendsRepositoryImpl implements FriendsRepository {
    private FirebaseHelper firebaseHelper;
    private ChildEventListener friendEventListener;
    private EventBus eventBus;

    public FriendsRepositoryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void removeFriend(String email) {
        String currentUserEmail = firebaseHelper.getAuthUserEmail();
        firebaseHelper.getOneFriendReference(currentUserEmail, email).removeValue();
        firebaseHelper.getOneFriendReference(email, currentUserEmail).removeValue();
    }

    @Override
    public void destroyFriendListListener() {
        friendEventListener = null;
    }

    @Override
    public void subscribeForFriendListUpdates() {
        if (friendEventListener == null) {
            friendEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                    handleChat(dataSnapshot,FriendListEvent.onFriendAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {
                    handleChat(dataSnapshot, FriendListEvent.onFriendChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleChat(dataSnapshot, FriendListEvent.onFriendRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(FirebaseError firebaseError) {}
            };
        }
        firebaseHelper.getMyFriendsReference().addChildEventListener(friendEventListener);
    }

    @Override
    public void unSubscribeForFriendListUpdates() {
        if (friendEventListener!= null) {
            firebaseHelper.getMyFriendsReference().removeEventListener(friendEventListener);
        }
    }

    private void handleChat(DataSnapshot dataSnapshot,final int type) {
        String email = dataSnapshot.getKey();
        email = email.replace("_",".");
        boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
        final User user = new User("Name",email, online, null);
        FirebaseHelper.getInstance().getUserReference(user.getEmail()).child("name").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        user.setName(dataSnapshot.getValue().toString());
                        postEvent(type, user);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
    }

    private void postEvent(int type, User user) {
        FriendListEvent friendListEvent = new FriendListEvent();
        friendListEvent.setEventType(type);
        friendListEvent.setUser(user);
        eventBus.post(friendListEvent);
    }
}
