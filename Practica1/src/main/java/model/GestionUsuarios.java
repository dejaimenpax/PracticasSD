package model;

import java.util.Hashtable;

public class GestionUsuarios {

    public Hashtable<String, Usuario> users = new Hashtable<>();

    public void removeUser(Usuario us){
        us.setState(false);
    }

    public void editUser(Usuario us, String password, String fullName,  boolean state){
        Usuario myUser = users.get(us.getId());
        myUser.setPassword(password);
        myUser.setFullName(fullName);
        myUser.setState(state);
        users.put(myUser.getId(), myUser);
    }

    public void addUser(Usuario us){
        users.put(us.getId(), us);
    }

}
