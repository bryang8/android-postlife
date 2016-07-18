package projects.bryang8.com.postlife.friends.addfriend;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import projects.bryang8.com.postlife.domain.FirebaseHelper;
import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.friends.addfriend.events.Friends;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public class SearchRepositoryImpl implements SearchRepository {
    private FirebaseHelper firebaseHelper;
    private ChildEventListener listener;
    private SearchPresenter presenter;

    public SearchRepositoryImpl(SearchPresenter presenter) {
        this.presenter = presenter;
        this.firebaseHelper = FirebaseHelper.getInstance();
    }

    @Override
    public void getFriends(final String _name) {
        Friends.setNew();
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    String auth = firebaseHelper.getAuthUserEmail();
                    String email = dataSnapshot.getKey().toString();
                    email = email.replace("_",".");
                    if(!email.equals(auth)){
                        if (name.toLowerCase().contains(_name.toLowerCase())
                            || email.toLowerCase().contains(_name.toLowerCase())){

                            Boolean online = (Boolean)dataSnapshot.child("online").getValue();

                            User user = new User(name, email,online,null);
                            user.setEmail(email);
                            Friends.addUser(user);
                        }
                    }
                    presenter.updateFriends();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            };
            firebaseHelper.getUsersReference().addChildEventListener(listener);
    }

    @Override
    public void addFiend(final String email) {
        final String key = email.replace(".","_");
        Firebase userReference = firebaseHelper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    Firebase myfriends = firebaseHelper.getMyFriendsReference();
                    myfriends.child(key).setValue(user.getName());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {}
        });
    }
}
