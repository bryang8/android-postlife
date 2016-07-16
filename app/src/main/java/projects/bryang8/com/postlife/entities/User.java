package projects.bryang8.com.postlife.entities;

import java.util.Map;

/**
 * Created by bryan_g8 on 14/07/16.
 */
public class User {
    String name;
    String email;
    boolean online;
    Map<String, Boolean> contacts;
    public final static boolean ONLINE = true;
    public final static boolean OFFLINE = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj instanceof User) {
            User recipe = (User) obj;
            equal = this.email.equals(recipe.getEmail());
        }
        return equal;
    }
}
