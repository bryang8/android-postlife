package projects.bryang8.com.postlife.posts.postlist.ui;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.entities.Post;
import projects.bryang8.com.postlife.lib.GlideImageLoader;
import projects.bryang8.com.postlife.lib.ImageLoader;
import projects.bryang8.com.postlife.lib.domain.AvatarHelper;
import projects.bryang8.com.postlife.lib.domain.FirebaseHelper;
import projects.bryang8.com.postlife.posts.addpost.AddPostFragment;
import projects.bryang8.com.postlife.posts.postlist.PostsPresenter;
import projects.bryang8.com.postlife.posts.postlist.PostsPresenterImpl;
import projects.bryang8.com.postlife.posts.postlist.ui.adapters.OnItemClickListener;
import projects.bryang8.com.postlife.posts.postlist.ui.adapters.PostsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment implements PostsView, OnItemClickListener {
    @Bind(R.id.recyclerPosts)
    RecyclerView recyclerView;
    @Bind(R.id.imgAvatar)
    CircleImageView imgAvatar;

    private PostsPresenter presenter;
    private PostsAdapter adapter;
    ImageLoader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.bind(this, view);

        loader = new GlideImageLoader(this.getActivity().getApplicationContext());
        loader.load(imgAvatar, AvatarHelper.getAvatarUrl(FirebaseHelper.getInstance().getAuthUserEmail()));

        presenter = new PostsPresenterImpl(this);
        presenter.onCreate();

        setupAdapter();
        setupRecyclerView();
        return view;
    }

    private void setupAdapter() {
        adapter = new PostsAdapter(new ArrayList<Post>(), loader, this, this.getActivity());
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void addPost(Post post) {
        adapter.add(post);
    }

    @Override
    public void removePost(Post post) {
        adapter.remove(post);
    }

    @Override
    public void updatePost(Post post){
        adapter.update(post);
    }

    @OnClick(R.id.cardNewPost)
    @Override
    public void makePost() {
        new AddPostFragment().show(getFragmentManager(),"");
    }

    @Override
    public void likePost(Post post) {
        presenter.likePost(post);
    }

    @Override
    public void onProfileClick(Post post) {

    }

    @Override
    public void onReportClick(){
        Snackbar.make(recyclerView, "Post enviado para revisión",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onFavClick(Post post) {
        likePost(post);
    }
}
