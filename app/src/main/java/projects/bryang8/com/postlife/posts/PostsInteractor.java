package projects.bryang8.com.postlife.posts;

import projects.bryang8.com.postlife.entities.Post;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public interface PostsInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void likePost(Post post);
    void dislikePost();
}
