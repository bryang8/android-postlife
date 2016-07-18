package projects.bryang8.com.postlife.login;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import projects.bryang8.com.postlife.domain.FirebaseHelper;
import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.lib.EventBus;
import projects.bryang8.com.postlife.lib.GreenRobotEventBus;
import projects.bryang8.com.postlife.login.events.LoginEvent;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class LoginRepositoryImpl implements LoginRepository{
    private FirebaseHelper helper;
    private Firebase dataReference;
    private Firebase myUserReference;

    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.dataReference =helper.getDataReference();
        this.myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signUp(final String name, final String email, final String password){
        dataReference.createUser(email, password, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                postEvent(LoginEvent.onSignUpSuccess);
                signIn(email, password);
                helper.addNameUser(name);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignUpError, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void signIn(String email, String password) {
        dataReference.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                initSignIn();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignInError, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void checkSession() {
        if (dataReference.getAuth() != null) {
            initSignIn();
        } else {
            postEvent(LoginEvent.onFailedToRecoverSession);
        }
    }
    private void initSignIn(){
        myUserReference = helper.getMyUserReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = new User(dataSnapshot.child("name").getValue().toString(),
                                    dataSnapshot.getKey().toString(),
                                    (Boolean)dataSnapshot.child("online").getValue(),
                                    null);
                if  (currentUser == null) {
                    registerNewUser();
                }
                helper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.onSignInSuccess);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {}
        });
    }

    private void registerNewUser(){
        String email = helper.getAuthUserEmail();
        if (email != null) {
            User currentUser = new User();
            currentUser.setEmail(email);
            myUserReference.setValue(currentUser);
        }
    }

    private void postEvent(int type, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage !=null ) {
            loginEvent.setErrorMesage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {
        postEvent(type,null);
    }
}