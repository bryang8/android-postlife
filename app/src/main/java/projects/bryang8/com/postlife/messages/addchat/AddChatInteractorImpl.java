package projects.bryang8.com.postlife.messages.addchat;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class AddChatInteractorImpl implements AddChatInteractor {
    AddChatRepository repository;

    public AddChatInteractorImpl() {
        repository = new AddChatRepositoryImpl();
    }

    @Override
    public void execute(String email) {
        repository.addContact(email);
    }
}
