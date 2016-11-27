package M1.Connecteur;

import M1.Interface.RoleFourni;
import M1.Interface.RoleRequis;
import M1.Serveur.ServeurDetail;
import M2.Connecteur.ConnecteurConcret;
import M2.Connecteur.Glue;
import M2.Interface.Interface;
import M2.Interface.Role;

/**
 * Created by Abdeldjallil on 14/11/2016.
 */
public class ClearanceRequest extends ConnecteurConcret {

    ServeurDetail sd;
    public ClearanceRequest(ServeurDetail sd) {
        super("Clearance Request");
        this.roles.add(new RoleRequis(this, "Caller"));
        this.roles.add(new RoleFourni(this, "CalledConnectionManager"));
        this.roles.add(new RoleFourni(this, "CalledSecurityManager"));
        this.sd = sd;

        glues.add(new Glue("versCalledConnectionManager", this){
            @Override public void coller(){
                roles.get(1).setInformation(roles.getFirst().getInformation());
                ((ClearanceRequest)connecteurConcret).notifierSystem(roles.get(1));// c'est 1 et non pas 0

            }
        });

        glues.getFirst().ajouterRole(roles.getFirst());
        glues.getFirst().ajouterRole(roles.get(1));
        //je pense qu'on doit instancier objet glue puis ajouter les roles pour appeler la méthode coller


        glues.add(new Glue("versCalledSecurityManager", this){
            @Override public void coller(){
                roles.get(1).setInformation(roles.getFirst().getInformation());
                //System.out.printf("Le ClearanceRequest notifier la configue pour transmettre le message du ConnectionManager vers le SecurityManager\n");
                ((ClearanceRequest)connecteurConcret).notifierSystem(roles.get(1));

            }
        });
        glues.get(1).ajouterRole(roles.getFirst());
        glues.get(1).ajouterRole(roles.get(2));
    }

    public void notifierSystem(Interface notifieur){
        sd.notification(notifieur);
    }

}
