package M1.Connecteur;

import M1.Interface.RoleFourni;
import M1.Interface.RoleRequis;
import M1.Serveur.ServeurDetail;
import M2.Connecteur.ConnecteurConcret;
import M2.Connecteur.Glue;
import M2.Interface.Interface;

/**
 * Created by Abdeldjallil on 14/11/2016.
 */
public class SecurityQuery extends ConnecteurConcret{

    ServeurDetail sd;
    public SecurityQuery(ServeurDetail sd) {
        super("Security Query");
        this.roles.add(new RoleRequis(this, "Caller"));
        this.roles.add(new RoleFourni(this, "CalledSecurityManager"));
        this.roles.add(new RoleFourni(this, "CalledDatabase"));
        this.sd = sd;

        glues.add(new Glue("versCalledSecurityManager", this){
            @Override public void coller(){
                roles.get(1).setInformation(roles.getFirst().getInformation());
                ((SecurityQuery)connecteurConcret).notifierSystem(roles.get(1));// c'est 1 et non pas 0

            }
        });

        glues.getFirst().ajouterRole(roles.getFirst());
        glues.getFirst().ajouterRole(roles.get(1));
        //je pense qu'on doit instancier objet glue puis ajouter les roles pour appeler la méthode coller


        glues.add(new Glue("versCalledDatabase", this){
            @Override public void coller(){
                roles.get(1).setInformation(roles.getFirst().getInformation());
                ((SecurityQuery)connecteurConcret).notifierSystem(roles.get(1));

            }
        });
        glues.get(1).ajouterRole(roles.getFirst());
        glues.get(1).ajouterRole(roles.get(2));
    }

    public void notifierSystem(Interface notifieur){
        sd.notification(notifieur);
    }

}
