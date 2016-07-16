package projects.bryang8.com.postlife.main;

import projects.bryang8.com.postlife.domain.FirebaseHelper;

/**
 * Created by bryan_g8 on 14/07/16.
 */
public class MainRepositoryImpl implements MainRepository {
    private FirebaseHelper firebaseHelper;

    public MainRepositoryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
    }

    @Override
    public void signOff() {
        firebaseHelper.signOff();
    }

    @Override
    public String getCurrentEmail() {
        return firebaseHelper.getAuthUserEmail();
    }

    @Override
    public void changeUserConnectionStatus(boolean online) {

    }
}
