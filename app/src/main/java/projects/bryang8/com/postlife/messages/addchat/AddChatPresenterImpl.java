package projects.bryang8.com.postlife.messages.addchat;

import org.greenrobot.eventbus.Subscribe;

import projects.bryang8.com.postlife.lib.EventBus;
import projects.bryang8.com.postlife.lib.GreenRobotEventBus;
import projects.bryang8.com.postlife.messages.addchat.events.AddChatEvent;
import projects.bryang8.com.postlife.messages.addchat.ui.AddChatView;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class AddChatPresenterImpl implements AddChatPresenter {
    private AddChatView view;
    private EventBus eventBus;
    private AddChatInteractor interactor;

    public AddChatPresenterImpl(AddChatView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddChatInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view =null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if (view != null) {
            view.hideInput();
            view.showProgress();
        }
        interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddChatEvent event) {
        if (view != null) {
            view.hideProgress();
            view.showInput();

            if (event.isError()) {
                view.contactAdded();
            } else {
                view.contactAdded();
            }
        }
    }
}
