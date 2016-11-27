package M1.Interface;

import M1.Serveur.ServeurDetail;
import M1.Systeme.SystemeCS;
import M2.ObjectArchi.ObjetArchitectural;

/**
 * Created by erhode on 11/10/16.
 */
public class PortConfigurationFourni extends M2.Interface.PortConfigurationFourni {
    public PortConfigurationFourni(ObjetArchitectural parent, String name) {
        super(parent, name);
    }

    @Override
    public void setInformation(String information) {
        this.information = information.toString();
        if (parent instanceof ServeurDetail) {
            System.out.printf("Le message est sur le port "+ this.getName() + "  du serveur Detail\n");
            ((ServeurDetail) parent).Notification();
        }
        else
            if (parent instanceof SystemeCS) {
                System.out.printf("Le message est sur le port "+ this.getName() + "  du SystemeCS\n");
                ((SystemeCS) parent).bind(this);
            }
    }
}
