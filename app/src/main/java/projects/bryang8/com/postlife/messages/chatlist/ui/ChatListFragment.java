package projects.bryang8.com.postlife.messages.chatlist.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.lib.GlideImageLoader;
import projects.bryang8.com.postlife.lib.ImageLoader;
import projects.bryang8.com.postlife.messages.chatlist.ChatListPresenter;
import projects.bryang8.com.postlife.messages.chatlist.ChatListPresenterImpl;
import projects.bryang8.com.postlife.messages.chatlist.ui.adapters.ChatListAdapter;
import projects.bryang8.com.postlife.messages.chatlist.ui.adapters.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment implements ChatListView, OnItemClickListener{

    @Bind(R.id.recyclerViewChats)
    RecyclerView recyclerView;

    private ChatListPresenter presenter;
    private ChatListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages_list, container, false);
        presenter = new ChatListPresenterImpl(this);
        presenter.onCreate();

        setupAdapter();
        setupRecyclerView();

        return view;
    }

    private void setupAdapter() {
        ImageLoader loader = new GlideImageLoader(this.getActivity().getApplicationContext());
        adapter = new ChatListAdapter(new ArrayList<User>(), loader, this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(adapter);
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

    @Override
    public void onChatAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onChatRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {

    }

    @Override
    public void onItemLongClick(User user) {

    }
}