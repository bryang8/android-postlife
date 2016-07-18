package projects.bryang8.com.postlife.posts.events;

import projects.bryang8.com.postlife.entities.Post;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public class PostsListEvent {
    private Post post;
    private int eventType;

    public final static int onPostAdded = 0;
    public final static int onPostRemoved = 1;
    public final static int onPostChanged = 2;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
