package projects.bryang8.com.postlife.posts.postlist.ui.adapters;

import projects.bryang8.com.postlife.entities.Post;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public interface OnItemClickListener {
    void onProfileClick(Post post);
    void onFavClick(Post post);
    void onReportClick();
}
