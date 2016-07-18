package projects.bryang8.com.postlife.friends.addfriend.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.domain.AvatarHelper;
import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.lib.ImageLoader;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public class SearchAdapter extends RecyclerView.Adapter <SearchAdapter.ViewHolder>{
    private List<User> friendList;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;
    private Context mContext;

    public SearchAdapter(List<User> contactList,
                           ImageLoader imageLoader,
                           OnItemClickListener clickListener, Context mContext) {
        this.friendList = contactList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_friend_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = friendList.get(position);
        holder.setClickListener(user, clickListener);
        holder.txtName.setText(user.getName());
        holder.txtEmail.setText(user.getEmail());

        imageLoader.load(holder.imgAvatar, AvatarHelper.getAvatarUrl(user.getEmail()));

        holder.toolbarCard.inflateMenu(R.menu.menu_search_item);
        holder.toolbarCard.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        clickListener.onMenuClick(user);
                        break;
                    case R.id.action_profile:
                        clickListener.onClick(user);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }


    public void setList(ArrayList<User> users) {
        friendList = users;
        this.notifyDataSetChanged();
    }

    public void clear() {
        if(friendList != null){
            friendList.clear();
            this.notifyDataSetChanged();
        }
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
                    listener.onClick(user);
                }
            });
        }
    }
}
