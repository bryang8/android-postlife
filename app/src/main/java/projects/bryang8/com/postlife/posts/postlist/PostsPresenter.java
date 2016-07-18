package projects.bryang8.com.postlife.posts.postlist;

import projects.bryang8.com.postlife.entities.Post;
import projects.bryang8.com.postlife.posts.postlist.events.PostsListEvent;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public interface PostsPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void likePost(Post post);
    void onEventMainThread(PostsListEvent event);
}
