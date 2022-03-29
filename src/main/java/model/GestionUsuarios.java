package model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

public class GestionUsuarios {

    public Hashtable<String, Usuario> users = new Hashtable<>();

    protected class Usuario {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private String id;

        private String password;
        private String fullName;
        private String entryDate;
        private boolean state;
        //private BufferedImage BufferedImage photo;

        private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");

        public String getId() {
            return id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEntryDate() {
            return entryDate;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public Usuario(String id, String password, String fullName){
            this.id = id;
            this.password = password;
            this.fullName = fullName;
            this.entryDate = dtf.format(LocalDate.now());
            state = true;
        }

    }

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
