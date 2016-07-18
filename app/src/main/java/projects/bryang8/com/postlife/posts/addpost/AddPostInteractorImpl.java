package projects.bryang8.com.postlife.posts.addpost;

import projects.bryang8.com.postlife.entities.Post;

/**
 * Created by bryan_g8 on 18/07/16.
 */
public class AddPostInteractorImpl implements AddPostInteractor {
    AddPostRepo repo;

    public AddPostInteractorImpl() {
        this.repo = new AddPostRepoImpl();
    }

    @Override
    public void execute(Post post) {
        repo.addPost(post);
    }
}
