package projects.bryang8.com.postlife.lib;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bryan_g8 on 14/07/16.
*/
public class GlideImageLoader implements ImageLoader{
    private RequestManager requestManager;

    public GlideImageLoader(Context context) {
        this.requestManager = Glide.with(context);
    }

    @Override
    public void load(CircleImageView imgAvatar, String url) {
        requestManager.load(url).into(imgAvatar);
    }
}
