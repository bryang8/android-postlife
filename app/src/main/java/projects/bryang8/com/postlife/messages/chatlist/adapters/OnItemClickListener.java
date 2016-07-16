package projects.bryang8.com.postlife.messages.chatlist.adapters;

import projects.bryang8.com.postlife.entities.User;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
