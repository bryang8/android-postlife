package projects.bryang8.com.postlife.posts.postlist;

import org.greenrobot.eventbus.Subscribe;

import projects.bryang8.com.postlife.entities.Post;
import projects.bryang8.com.postlife.lib.EventBus;
import projects.bryang8.com.postlife.lib.GreenRobotEventBus;
import projects.bryang8.com.postlife.posts.postlist.events.PostsListEvent;
import projects.bryang8.com.postlife.posts.postlist.ui.PostsView;

/**
 * Created by bryan_g8 on 18/07/16.
 */
public class PostsPresenterImpl implements  PostsPresenter{
    private EventBus eventBus;
    private PostsView view;
    private PostsInteractor interactor;

    public PostsPresenterImpl(PostsView view) {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.view = view;
        this.interactor = new PostsInteractorImpl();
    }

    @Override
    public void onPause() {
        interactor.unsubscribe();
    }

    @Override
    public void onResume() {
        interactor.subscribe();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        interactor.destroyListener();
        view = null;
    }

    @Override
    public void likePost(Post post) {
        interactor.likePost(post);
    }

    @Override
    @Subscribe
    public void onEventMainThread(PostsListEvent event) {
        Post post = event.getPost();
        switch (event.getEventType()){
            case PostsListEvent.onPostAdded:
                onPostAdded(post);
                break;
            case PostsListEvent.onPostChanged:
                onPostChanged(post);
                break;
            case PostsListEvent.onPostRemoved:
                onPostRemoved(post);
                break;
        }
    }

    public void onPostAdded(Post post) {
        if (view != null) {
            view.addPost(post);
        }
    }

    public void onPostChanged(Post post) {
        if (view != null) {
            view.updatePost(post);
        }
    }

    public void onPostRemoved(Post post) {
        if (view != null) {
            view.removePost(post);
        }
    }
}
