package projects.bryang8.com.postlife.main;

import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.main.ui.MainActivity;

/**
 * Created by bryan_g8 on 14/07/16.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainActivity view;
    private MainSessionInteractor mainInteractor;

    public MainPresenterImpl(MainActivity view) {
        this.view = view;
        this.mainInteractor = new MainSessionInteractorImpl();
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onPause() {
        mainInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onResume() {
        mainInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void signOff() {
        mainInteractor.changeConnectionStatus(User.OFFLINE);
        mainInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return mainInteractor.getCurrentUserEmail();
    }

}
