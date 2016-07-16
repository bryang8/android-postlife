package projects.bryang8.com.postlife.main;

/**
 * Created by bryan_g8 on 14/07/16.
 */
public class MainSessionInteractorImpl implements MainSessionInteractor {
    MainRepositoryImpl mainRepository;

    public MainSessionInteractorImpl() {
        this.mainRepository = new MainRepositoryImpl();
    }

    @Override
    public void signOff() {
        mainRepository.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return mainRepository.getCurrentEmail();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        mainRepository.changeUserConnectionStatus(online);
    }
}
