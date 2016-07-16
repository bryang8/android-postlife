package projects.bryang8.com.postlife.login;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface LoginInteractor {
    void checkSession();
    void doSignIn(String email, String password);
    void doSignUp(String name, String email, String password);
}
