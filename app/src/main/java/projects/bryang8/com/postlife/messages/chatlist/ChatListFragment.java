package projects.bryang8.com.postlife.messages.chatlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import projects.bryang8.com.postlife.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment {


    public ChatListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages_list, container, false);
        return view;
    }

}
