package M2.Connecteur;

import M2.Interface.Role;

import java.util.LinkedList;

/**
 * Created by erhode on 10/28/16.
 */
public abstract class ConnecteurConcret extends ConnecteurExplicite {
    public LinkedList<Glue> glues;
    public LinkedList<Role> roles;
    public String name;

    public ConnecteurConcret(String name) {
        this.name = name;
        this.glues = new LinkedList<Glue>();
        this.roles = new LinkedList<Role>();
    }

    public LinkedList<Glue> getGlues() {
        return glues;
    }

    public void setGlues(LinkedList<Glue> glues) {
        this.glues = glues;
    }

    public LinkedList<Role> getRoles() {
        return roles;
    }

    public void setRoles(LinkedList<Role> roles) {
        this.roles = roles;
    }
    public Role getRole(Integer index){
        return roles.get(index);
    }
}
