package projects.bryang8.com.postlife.friends.friendslist.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.entities.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment implements FriendsView{
    @Bind(R.id.recyclerViewFriends)
    RecyclerView recyclerView;

    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onFriendAdded(User user) {

    }

    @Override
    public void onFriendChanged(User user) {

    }

    @Override
    public void onFriendRemoved(User user) {

    }
}
