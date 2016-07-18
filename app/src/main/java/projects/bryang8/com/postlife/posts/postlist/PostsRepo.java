package projects.bryang8.com.postlife.posts.postlist;

import projects.bryang8.com.postlife.entities.Post;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public interface PostsRepo {
    void likePost(Post post);
    void destroyListener();
    void subscribe();
    void unSubscribe();
}
