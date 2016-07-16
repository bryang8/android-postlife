package projects.bryang8.com.postlife.main.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.friends.FriendsFragment;
import projects.bryang8.com.postlife.login.ui.LoginActivity;
import projects.bryang8.com.postlife.main.MainPresenter;
import projects.bryang8.com.postlife.main.MainPresenterImpl;
import projects.bryang8.com.postlife.main.adapters.MainPagerAdapter;
import projects.bryang8.com.postlife.messages.chatlist.ui.ChatListFragment;
import projects.bryang8.com.postlife.posts.PostsFragment;
import projects.bryang8.com.postlife.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.container)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupAdapter();

        mainPresenter = new MainPresenterImpl(this);
    }

    private void setupAdapter() {
        Fragment[] fragments = new Fragment[] {new PostsFragment(),
                                                new ChatListFragment(),
                                                new FriendsFragment(),
                                                new ProfileFragment()};

        String[] titles = new String[] {getString(R.string.main_header_posts),
                                        getString(R.string.main_header_messages),
                                        getString(R.string.main_header_friends),
                                        getString(R.string.main_header_profile)};

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);

        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactlist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            mainPresenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
