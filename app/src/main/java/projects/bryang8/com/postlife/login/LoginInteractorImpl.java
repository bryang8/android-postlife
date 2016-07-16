package projects.bryang8.com.postlife.login;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class LoginInteractorImpl implements LoginInteractor{
    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
        loginRepository.checkSession();
    }


    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }

    @Override
    public void doSignUp(String name, String email, String password) {
        loginRepository.signUp(name, email, password);
    }
}
