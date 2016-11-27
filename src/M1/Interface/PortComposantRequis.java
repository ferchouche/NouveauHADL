package M1.Interface;

import M1.Client.Client;
import M1.ServerDetail.ConnectionManager;
import M1.ServerDetail.DataBase;
import M1.ServerDetail.SecurityManager;
import M1.Serveur.Serveur;
import M2.ObjectArchi.ObjetArchitectural;

/**
 * Created by erhode on 11/10/16.
 */
public class PortComposantRequis extends M2.Interface.PortComposantRequis{

    public PortComposantRequis(ObjetArchitectural parent, String name){
        super(parent,name);
    }

    @Override public void setInformation(String information){
        this.information = information;
        if (this.parent instanceof Serveur) {
            System.out.printf("Le message est sur le port  "+ this.getName() + "  du serveur\n");
            ((Serveur)parent).notifierSystem(this);
        }
        else
            if (this.parent instanceof ConnectionManager){
                System.out.printf("Le message est sur le port  "+ this.getName() + "  du Connection manager\n");
                ((ConnectionManager)parent).traiter(this, information);
        }
        else
                if (this.parent instanceof Client) {
                    System.out.printf("Le message est sur le port  "+ this.getName() + "  du Client\n");
                }
            else
                if (this.parent instanceof SecurityManager) {
                    System.out.printf("Le message est sur le port  "+ this.getName() + " du SecurityManager\n");
                    ((SecurityManager)parent).traiter(this, information);
                }
                else
                if (this.parent instanceof DataBase) {
                    System.out.printf("Le message est sur le port  "+ this.getName() + " du DataBase\n");
                    ((DataBase)parent).traiter(this, information);
        }
    }
}