package projects.bryang8.com.postlife.friends.addfriend.ui;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import projects.bryang8.com.postlife.R;
import projects.bryang8.com.postlife.entities.User;
import projects.bryang8.com.postlife.friends.addfriend.SearchPresenter;
import projects.bryang8.com.postlife.friends.addfriend.SearchPresenterImpl;
import projects.bryang8.com.postlife.friends.addfriend.events.Friends;
import projects.bryang8.com.postlife.friends.addfriend.ui.adapters.OnItemClickListener;
import projects.bryang8.com.postlife.friends.addfriend.ui.adapters.SearchAdapter;
import projects.bryang8.com.postlife.lib.GlideImageLoader;
import projects.bryang8.com.postlife.lib.ImageLoader;

public class SearchActivity extends AppCompatActivity implements OnItemClickListener, projects.bryang8.com.postlife.friends.addfriend.ui.SearchView{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerViewFriends)
    RecyclerView recyclerView;
    private SearchPresenter presenter;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setTitle("");

        setSupportActionBar(toolbar);

        presenter = new SearchPresenterImpl(this);
        setupAdapter();
        setupRecyclerView();
    }

    private void setupAdapter() {
        ImageLoader loader = new GlideImageLoader(this.getApplicationContext());
        adapter = new SearchAdapter(new ArrayList<User>(), loader, this, this.getApplicationContext());
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getText(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("")){
                    clear();
                }else{
                    presenter.search(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")){
                    clear();
                }else{
                    presenter.search(newText);
                }
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setFriends() {
        ArrayList<User> users = Friends.getUsers();
        adapter.setList(users);
    }

    @Override
    public void clear() {
        adapter.clear();
        Friends.setNew();
    }


    @Override
    public void onClick(User user) {
        Toast.makeText(this.getApplicationContext(),"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuClick(User user) {
        presenter.addFriend(user.getEmail());
    }
}
