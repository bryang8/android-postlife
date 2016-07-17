package projects.bryang8.com.postlife.messages.chatlist;

/**
 * Created by bryan_g8 on 16/07/16.
 */
public class ChatListInteractorImpl implements ChatListInteractor {
    ChatListRepository repository;

    public ChatListInteractorImpl() {
        this.repository = new ChatListRepositoryImpl();
    }

    @Override
    public void subscribe() {
        repository.subscribeForChatListUpdates();
    }

    @Override
    public void unsubscribe() {
        repository.unSubscribeForChatListUpdates();
    }

    @Override
    public void destroyListener() {
        repository.destroyChatListListener();
    }

    @Override
    public void removeChat(String email) {
        repository.removeChat(email);
    }
}
