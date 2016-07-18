package projects.bryang8.com.postlife.posts;

import projects.bryang8.com.postlife.chat.events.ChatEvent;
import projects.bryang8.com.postlife.entities.Post;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public interface PostsPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void likePost(Post post);
    void dislike(Post post);
    void onEventMainThread(ChatEvent event);
}
