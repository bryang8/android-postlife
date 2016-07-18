package projects.bryang8.com.postlife.posts.postlist;

import projects.bryang8.com.postlife.entities.Post;

/**
 * Created by bryan_g8 on 18/07/16.
 */
public class PostsInteractorImpl implements PostsInteractor {
    private PostsRepo repo;

    public PostsInteractorImpl() {
        this.repo = new PostRepoImpl();
    }

    @Override
    public void subscribe() {
        repo.subscribe();
    }

    @Override
    public void unsubscribe() {
        repo.unSubscribe();
    }

    @Override
    public void destroyListener() {
        repo.destroyListener();
    }

    @Override
    public void likePost(Post post) {
        repo.likePost(post);
    }
}
