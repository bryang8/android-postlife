package projects.bryang8.com.postlife.posts.postlist;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import projects.bryang8.com.postlife.entities.Post;
import projects.bryang8.com.postlife.lib.EventBus;
import projects.bryang8.com.postlife.lib.GreenRobotEventBus;
import projects.bryang8.com.postlife.lib.domain.FirebaseHelper;
import projects.bryang8.com.postlife.posts.postlist.events.PostsListEvent;

/**
 * Created by bryan_g8 on 18/07/16.
 */
public class PostRepoImpl implements PostsRepo {
    private FirebaseHelper helper;
    private ChildEventListener listener;
    private ValueEventListener likeListener;
    private ChildEventListener friendsListener;
    private EventBus eventBus;
    private final static String DATE = "date";
    private final static String LIKES_NUM = "likesnum";
    private final static String LIKES = "likes";

    public PostRepoImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void likePost(final Post post) {
        likeListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String emailAuth = helper.getAuthUserEmail().replace(".", "_");
                if(dataSnapshot.child(LIKES).child(emailAuth).exists() &&
                        (Boolean)dataSnapshot.child(LIKES).child(emailAuth).getValue()){

                    helper.getOnePostReference(post).child(LIKES).child(emailAuth)
                            .child(emailAuth).setValue(false);

                    int likesNum = Integer.parseInt(dataSnapshot
                            .child(LIKES_NUM).getValue().toString());

                    helper.getOnePostReference(post).child(LIKES_NUM).setValue(likesNum - 1);
                }
                else {
                    helper.getOnePostReference(post).child(LIKES).child(emailAuth).setValue(true);

                    helper.getOnePostReference(post).child(LIKES_NUM).setValue(
                            Integer.parseInt(dataSnapshot.child(LIKES_NUM).toString()) - 1
                    );
                }
                /*PostsListEvent event = new PostsListEvent();
                event.setEventType(PostsListEvent.onPostChanged);
                event.setPost(post);
                eventBus.post(event);*/
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        };
        helper.getOnePostReference(post).addListenerForSingleValueEvent(likeListener);
    }

    @Override
    public void destroyListener() {
        listener = null;
    }

    @Override
    public void subscribe() {
        if (listener == null){
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handlePost(dataSnapshot,PostsListEvent.onPostAdded);
                    friendsListener();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handlePost(dataSnapshot,PostsListEvent.onPostChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handlePost(dataSnapshot,PostsListEvent.onPostRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            };
        }
    }

    public void friendsListener(){
        if (friendsListener == null) {
            friendsListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                   helper.getUserPostRefernce(dataSnapshot.getKey()).addChildEventListener(listener);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    helper.getUserPostRefernce(dataSnapshot.getKey()).removeEventListener(listener);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(FirebaseError firebaseError) {}
            };
        }
        helper.getMyFriendsReference().addChildEventListener(friendsListener);
    }

    @Override
    public void unSubscribe() {

    }

    private void handlePost(DataSnapshot dataSnapshot,final int type){
        final Post post = dataSnapshot.getValue(Post.class);
        postEvent(type, post);

    }

    private void postEvent(int type, Post post) {
        PostsListEvent postListEvent = new PostsListEvent();
        postListEvent.setEventType(type);
        postListEvent.setPost(post);
        eventBus.post(postListEvent);
    }
}
