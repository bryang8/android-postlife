package projects.bryang8.com.postlife.friends.addfriend;

import projects.bryang8.com.postlife.friends.addfriend.ui.SearchView;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public class SearchPresenterImpl implements SearchPresenter{
    private SearchView view;
    private SearchRepository repository;

    public SearchPresenterImpl(SearchView view) {
        this.repository = new SearchRepositoryImpl(this);
        this.view = view;
    }

    @Override
    public void search(String name) {
        repository.getFriends(name);
    }

    @Override
    public void addFriend(String email) {
        repository.addFiend(email);
    }

    @Override
    public void updateFriends(){
        view.setFriends();
    }
}
