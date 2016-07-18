package projects.bryang8.com.postlife.posts.addpost.events;

/**
 * Created by bryan_g8 on 18/07/16.
 */
public class AddPostEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
            this.error = error;
        }
}
