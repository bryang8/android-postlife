package projects.bryang8.com.postlife.posts.addpost;

/**
 * Created by bryan_g8 on 18/07/16.
 */
public interface AddPostView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void postAdded();
}
