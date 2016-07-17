package projects.bryang8.com.postlife.messages.chatlist.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.domain.AvatarHelper;
import projects.bryang8.com.postlife.domain.FirebaseHelper;
import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.lib.ImageLoader;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class ChatListAdapter extends RecyclerView.Adapter <ChatListAdapter.ViewHolder> {
    private List<User> contactList;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ChatListAdapter(List<User> contactList,
                           ImageLoader imageLoader,
                           OnItemClickListener clickListener) {
        this.contactList = contactList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = contactList.get(position);
        holder.setClickListener(user, clickListener);
        boolean online = user.isOnline();
        String status = online ? "online" : "";

        holder.txtUser.setText(user.getName());
        holder.txtStatus.setText(status);

        imageLoader.load(holder.imgAvatar, AvatarHelper.getAvatarUrl(user.getEmail()));

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    public int getPositionByUsername(String username) {
        int position = 0;
        for (User user : contactList) {
            if (user.getEmail().equals(username)) {
                break;
            }
            position++;
        }

        return position;
    }

    private boolean alreadyInAdapter(User newUser){
        boolean alreadyInAdapter = false;
        for (User user : this.contactList) {
            if (user.getEmail().equals(newUser.getEmail())) {
                alreadyInAdapter = true;
                break;
            }
        }

        return alreadyInAdapter;
    }

    public void add(User user) {
        if (!alreadyInAdapter(user)) {
            this.contactList.add(user);
            this.notifyDataSetChanged();
        }
    }

    public void update(User user) {
        int pos = getPositionByUsername(user.getEmail());
        contactList.set(pos, user);
        this.notifyDataSetChanged();
    }

    public void remove(User user) {
        int pos = getPositionByUsername(user.getEmail());
        contactList.remove(pos);
        this.notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgAvatar)
        CircleImageView imgAvatar;
        @Bind(R.id.txtStatus)
        TextView txtStatus;
        @Bind(R.id.txtUser)
        TextView txtUser;
        View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }

        public void setClickListener(final User user,
                                     final OnItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(user);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(user);
                    return false;
                }
            });
        }
    }
}