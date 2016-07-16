package projects.bryang8.com.postlife.login;

import projects.bryang8.com.postlife.login.events.LoginEvent;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void onPause();
    void onResume();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void onEventMainThread(LoginEvent event);
    void registerNewUser(String name, String email, String password);
}
