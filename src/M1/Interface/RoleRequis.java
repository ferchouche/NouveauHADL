package M1.Interface;

import M1.Connecteur.ClearanceRequest;
import M1.Connecteur.RPC;
import M1.Connecteur.SQL_Query;
import M1.Connecteur.SecurityQuery;
import M1.ServerDetail.ConnectionManager;
import M1.ServerDetail.DataBase;
import M1.ServerDetail.SecurityManager;
import M2.ObjectArchi.ObjetArchitectural;

/**
 * Created by erhode on 11/10/16.
 */
public class RoleRequis extends M2.Interface.Role{
    public RoleRequis(ObjetArchitectural parent, String name){
        super(parent, name);
    }

    @Override public void setInformation(ObjetArchitectural emetteur, String information) {
        this.information = information;
        if (this.parent instanceof RPC) {
            if (emetteur.getClass().getName().equals("M1.Client.Client")) { // Si le client est emetteur
                // System.out.printf("Le RPC recoi le message du client\n");
                ((RPC) parent).getGlues().get(1).coller(); // on colle vers le serveur
            } else {
                ((RPC) parent).getGlues().get(0).coller(); // sinon on colle vers le client
            }
        } else if (this.parent instanceof ClearanceRequest) {
            if (emetteur instanceof ConnectionManager) {
                //  System.out.printf("Le ClearanceRequest recoi le message du ConnectionManager\n");
                ((ClearanceRequest) parent).getGlues().get(1).coller();
            } else {
                ((ClearanceRequest) parent).getGlues().get(0).coller();
            }
        } else if (this.parent instanceof SecurityQuery) {
            if (emetteur instanceof SecurityManager)
                ((SecurityQuery) parent).getGlues().get(1).coller();
            else
                ((SecurityQuery) parent).getGlues().get(0).coller();
        } else if (this.parent instanceof SQL_Query) {
            if (emetteur instanceof ConnectionManager)
                ((SQL_Query) parent).getGlues().get(1).coller();
            else
                ((SQL_Query) parent).getGlues().get(0).coller();
        }
    }
}
