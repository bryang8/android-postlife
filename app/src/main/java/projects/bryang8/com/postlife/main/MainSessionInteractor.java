package projects.bryang8.com.postlife.main;

/**
 * Created by bryan_g8 on 14/07/16.
 */
public interface MainSessionInteractor {
    void changeConnectionStatus(boolean offline);

    void signOff();

    String getCurrentUserEmail();
}
