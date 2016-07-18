package projects.bryang8.com.postlife.friends.addfriend.events;

import java.util.ArrayList;

import projects.bryang8.com.postlife.entities.User;

/**
 * Created by bryan_g8 on 17/07/16.
 */
public class Friends{
    private static ArrayList<User> users;

    public static ArrayList<User> getUsers() {
        return users;
    }


    public static void addUser(User user){
        users.add(user);
    }


    public static void setNew() {
        users = new ArrayList<>();
    }
}
