package projects.bryang8.com.postlife.messages.addchat;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import projects.bryang8.com.postlife.domain.FirebaseHelper;
import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.lib.EventBus;
import projects.bryang8.com.postlife.lib.GreenRobotEventBus;
import projects.bryang8.com.postlife.messages.addchat.events.AddChatEvent;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class AddChatRepositoryImpl implements AddChatRepository {
    private EventBus eventBus;
    private FirebaseHelper helper;

    public AddChatRepositoryImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void addContact(final String email) {
        final String key = email.replace(".","_");
        Firebase userReference = helper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    Firebase myContactReference = helper.getMyContactsReference();
                    myContactReference.child(key).setValue(user.isOnline());

                    String currentUserEmailKey = helper.getAuthUserEmail();
                    currentUserEmailKey = currentUserEmailKey.replace(".","_");

                    Firebase reverseContactReference = helper.getContactsReference(email);
                    reverseContactReference.child(currentUserEmailKey).setValue(User.ONLINE);

                    postSuccess();
                } else {
                    postError();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                postError();
            }
        });
    }

    private void postError() {
        post(true);
    }

    private void postSuccess() {
        post(false);
    }

    private void post(boolean error) {
        AddChatEvent event = new AddChatEvent();
        event.setError(error);
        eventBus.post(event);
    }
}
