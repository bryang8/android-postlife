package projects.bryang8.com.postlife.friends.friendslist.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.lib.domain.AvatarHelper;
import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.lib.ImageLoader;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public class FriendsAdapter extends RecyclerView.Adapter <FriendsAdapter.ViewHolder> {
    private List<User> contactList;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public FriendsAdapter(List<User> contactList,
                          ImageLoader imageLoader,
                          OnItemClickListener clickListener) {
        this.contactList = contactList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_friend_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = contactList.get(position);
        holder.setClickListener(user, clickListener);
        boolean online = user.isOnline();
        String status = online ? "online" : "";

        holder.txtName.setText(user.getName());
        holder.txtEmail.setText(user.getEmail());

        imageLoader.load(holder.imgAvatar, AvatarHelper.getAvatarUrl(user.getEmail()));

        holder.toolbarCard.getMenu().clear();
        holder.toolbarCard.inflateMenu(R.menu.menu_friend_item);
        holder.toolbarCard.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        clickListener.onMenuClick(user);
                        break;
                }
                return true;
            }
        });
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

    private boolean alreadyInAdapter(User newUser) {
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
        @Bind(R.id.txtEmail)
        TextView txtEmail;
        @Bind(R.id.txtName)
        TextView txtName;
        View view;
        @Bind(R.id.toolbarCard)
        Toolbar toolbarCard;

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
        }
    }
}
