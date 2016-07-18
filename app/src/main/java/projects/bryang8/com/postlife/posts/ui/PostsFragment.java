package projects.bryang8.com.postlife.posts.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.entities.Post;
import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.posts.ui.adapters.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment implements PostsView, OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        return view;
    }

    @Override
    public void addPost(Post post) {

    }

    @Override
    public void removePost(Post post) {

    }

    @Override
    public void makePost() {

    }

    @Override
    public void likePost(Post post) {

    }

    @Override
    public void dislike(Post post) {

    }

    @Override
    public void onMenuClick(Post post) {

    }

    @Override
    public void onFavClick(Post post) {

    }
}
