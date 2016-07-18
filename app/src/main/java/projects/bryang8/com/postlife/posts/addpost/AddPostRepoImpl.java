package projects.bryang8.com.postlife.posts.addpost;

import projects.bryang8.com.postlife.entities.Post;
import projects.bryang8.com.postlife.lib.EventBus;
import projects.bryang8.com.postlife.lib.GreenRobotEventBus;
import projects.bryang8.com.postlife.lib.domain.FirebaseHelper;
import projects.bryang8.com.postlife.posts.addpost.events.AddPostEvent;

/**
 * Created by bryan_g8 on 18/07/16.
 */
public class AddPostRepoImpl implements AddPostRepo {
    private EventBus eventBus;
    private FirebaseHelper helper;

    public AddPostRepoImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void addPost(Post post) {
        helper.getUserPostRefernce(post.getEmail_poster())
                .child(post.getDate().toString()).setValue(post);
        AddPostEvent event = new AddPostEvent();
        eventBus.post(event);
    }
}
