package projects.bryang8.com.postlife.messages.addchat.events;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class AddChatEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
