package projects.bryang8.com.postlife.posts.ui;

import projects.bryang8.com.postlife.entities.Post;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public interface PostsView {
    void addPost(Post post);
    void removePost(Post post);
    void makePost();
    void likePost(Post post);
    void dislike(Post post);
}
