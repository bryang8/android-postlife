package projects.bryang8.com.postlife.lib;

/**
 * Created by bryan_g8 on 14/07/16.
 */
public interface EventBus {
    void register (Object suscriber);
    void unregister (Object suscriber);
    void post (Object event);
}
