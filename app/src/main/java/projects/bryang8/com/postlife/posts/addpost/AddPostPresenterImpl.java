package projects.bryang8.com.postlife.posts.addpost;

import org.greenrobot.eventbus.Subscribe;

import projects.bryang8.com.postlife.entities.Post;
import projects.bryang8.com.postlife.lib.EventBus;
import projects.bryang8.com.postlife.lib.GreenRobotEventBus;
import projects.bryang8.com.postlife.posts.addpost.events.AddPostEvent;

/**
 * Created by bryan_g8 on 18/07/16.
 */
public class AddPostPresenterImpl implements AddPostPresenter {
    private AddPostView view;
    private EventBus eventBus;
    private AddPostInteractor interactor;

    public AddPostPresenterImpl(AddPostView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddPostInteractorImpl();
    }

    @Override
    public void addPost(Post post) {
        if (view != null) {
            view.hideInput();
            view.showProgress();
        }
        interactor.execute(post);
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddPostEvent event) {
        if (view != null) {
            view.hideProgress();
            view.showInput();

            if (event.isError()) {
                view.postAdded();
            } else {
                view.postAdded();
            }
        }
    }
}
