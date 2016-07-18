package projects.bryang8.com.postlife.posts.postlist.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.entities.Post;
import projects.bryang8.com.postlife.lib.ImageLoader;
import projects.bryang8.com.postlife.lib.domain.AvatarHelper;
import projects.bryang8.com.postlife.lib.domain.FirebaseHelper;
import projects.bryang8.com.postlife.posts.postlist.helper.DateManager;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public class PostsAdapter extends RecyclerView.Adapter <PostsAdapter.ViewHolder> {
    private List<Post> postsList;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;
    private Context mContext;

    public PostsAdapter(List<Post> postsList, ImageLoader imageLoader,
                        OnItemClickListener clickListener, Context mContext) {
        this.postsList = postsList;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Post post =  postsList.get(position);

        holder.txtName.setText(post.getName_poster());
        holder.txtEmail.setText(post.getEmail_poster());
        holder.txtContent.setText(post.getContent());

        holder.txtDate.setText(DateManager.calculateTime(post.getDate()));
        holder.txtFavsNum.setText(post.getLikesNum().toString());

        imageLoader.load(holder.imgAvatar, AvatarHelper.getAvatarUrl(post.getEmail_poster()));

        holder.imgLike.setImageResource(R.drawable.nonfavorite);
        String email = FirebaseHelper.getInstance().getAuthUserEmail().replace(".","_");
        if (post.getLikes() != null){
            if(post.getLikes().containsKey(email)){
                if(post.getLikes().get(email)){
                    holder.imgLike.setImageResource(R.drawable.favorite);
                }
            }
        }
        holder.toolbarCard.getMenu().clear();
        holder.toolbarCard.inflateMenu(R.menu.menu_posts_item);
        holder.toolbarCard.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_report:
                        clickListener.onReportClick();
                }
                return true;
            }
        });
        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onFavClick(post);
            }
        });
        holder.imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onProfileClick(post);
            }
        });
    }



    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public int getPositionByDate(Date date) {
        int position = 0;
        for (Post post : postsList) {
            if (post.getDate().equals(date)) {
                break;
            }
            position++;
        }

        return position;
    }

    private boolean alreadyInAdapter(Post _post){
        boolean alreadyInAdapter = false;
        for (Post post : this.postsList) {
            if (post.getEmail_poster().equals(_post.getEmail_poster())
                    && post.getDate().equals(_post.getDate())) {
                alreadyInAdapter = true;
                break;
            }
        }

        return alreadyInAdapter;
    }

    public void add(Post post) {
        if (!alreadyInAdapter(post)) {
            this.postsList.add(post);
            this.notifyDataSetChanged();
        }
    }

    public void update(Post post) {
        int pos = getPositionByDate(post.getDate());
        postsList.set(pos, post);
        this.notifyDataSetChanged();
    }

    public void remove(Post post) {
        int pos = getPositionByDate(post.getDate());
        try {
            postsList.remove(pos);
        }catch(IndexOutOfBoundsException ix){}
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgAvatar)
        CircleImageView imgAvatar;
        @Bind(R.id.txtName)
        TextView txtName;
        @Bind(R.id.txtEmail)
        TextView txtEmail;
        @Bind(R.id.txtContent)
        TextView txtContent;
        @Bind(R.id.txtDate)
        TextView txtDate;
        @Bind(R.id.imgLike)
        ImageView imgLike;
        @Bind(R.id.toolbarCard)
        Toolbar toolbarCard;
        @Bind(R.id.txtFavsNum)
        TextView txtFavsNum;

        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
