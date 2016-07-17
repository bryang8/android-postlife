package projects.bryang8.com.postlife.friends.friendslist.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.friends.addfriend.SearchActivity;
import projects.bryang8.com.postlife.friends.friendslist.FriendsPresenter;
import projects.bryang8.com.postlife.friends.friendslist.FriendsPresenterImpl;
import projects.bryang8.com.postlife.friends.friendslist.ui.adapters.FriendsAdapter;
import projects.bryang8.com.postlife.friends.friendslist.ui.adapters.OnItemClickListener;
import projects.bryang8.com.postlife.lib.GlideImageLoader;
import projects.bryang8.com.postlife.lib.ImageLoader;
import projects.bryang8.com.postlife.messages.addchat.ui.AddChatFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment implements FriendsView, OnItemClickListener{

    @Bind(R.id.recyclerFriends)
    RecyclerView recyclerView;

    private FriendsPresenter presenter;
    private FriendsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        ButterKnife.bind(this, view);

        presenter = new FriendsPresenterImpl(this);
        presenter.onCreate();

        setupAdapter();
        setupRecyclerView();

        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setupAdapter() {
        ImageLoader loader = new GlideImageLoader(this.getActivity().getApplicationContext());
        adapter = new FriendsAdapter(new ArrayList<User>(), loader, this);
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
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

    @OnClick(R.id.fabFriends)
    public void addFriend(){
        startActivity(new Intent(this.getActivity(), SearchActivity.class));
    }

    @Override
    public void onFriendAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onFriendChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onFriendRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {

    }

    @Override
    public void onItemLongClick(User user) {

    }
}
