package projects.bryang8.com.postlife.main;

/**
 * Created by bryan_g8 on 14/07/16.
 */
public interface MainPresenter {
    void onDestroy();
    void onPause();
    void onResume();
    void signOff();
    String getCurrentUserEmail();
}
