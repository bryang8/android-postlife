package projects.bryang8.com.postlife.posts.addpost;

import projects.bryang8.com.postlife.entities.Post;
import projects.bryang8.com.postlife.posts.addpost.events.AddPostEvent;

/**
 * Created by bryan_g8 on 18/07/16.
 */
public interface AddPostPresenter {
    void addPost(Post post);

    void onShow();

    void onDestroy();

    void onEventMainThread(AddPostEvent event);
}
