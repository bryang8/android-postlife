package projects.bryang8.com.postlife;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by bryan_g8 on 14/07/16.
 */
public class PostlifeApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase(){
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
