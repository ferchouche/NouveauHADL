package M1.ServerDetail;

import M1.Interface.PortComposantFourni;
import M1.Interface.PortComposantRequis;
import M1.Serveur.ServeurDetail;
import M2.Composant.ComposantConcret;
import M2.Interface.Interface;
import M2.Interface.PortComposant;
import M2.ObjectArchi.ObjetArchitectural;

import java.time.Clock;

/**
 * Created by Abdeldjallil on 11/11/2016.
 */
public class ConnectionManager extends ComposantConcret{

    ServeurDetail sd;
    public ConnectionManager(ServeurDetail sd) {
        super("Connection Manager");
        this.portsRequis.add(new PortComposantRequis(this, "external_socket_Requis"));
        this.portsRequis.add(new PortComposantRequis(this, "security_check_Requis"));
        this.portsRequis.add(new PortComposantRequis(this, "dbQuery_Requis"));

        this.portsFournis.add(new PortComposantFourni(this, "external_socket_Fourni"));
        this.portsFournis.add(new PortComposantFourni(this, "security_check_Fourni"));
        this.portsFournis.add(new PortComposantFourni(this, "dbQuery_Fourni"));

        this.sd = sd;
    }

    public PortComposantFourni getFourni(int i) {
        return (PortComposantFourni)portsFournis.get(i); // without cast, function returning M2 PortComposantFourni
    }


    public PortComposantRequis getRequis(int i){
        return (PortComposantRequis)portsRequis.get(i); // without cast, function returning M2 PortComposantRequis
    }

    public void notifierSystem(Interface notifieur){
        if (notifieur.getName() == "external_socket_Fourni")
           sd.notifierBinding(notifieur);
        else
            sd.notification(notifieur, this);

    }

    public void traiter(PortComposant pc, String requete){
        if (pc.getName() == "external_socket_Requis"){
            // port du security manager
            this.portsFournis.get(1).setInformation(requete);
        } else if (pc.getName() == "security_check_Requis"){
            if(requete.contains("|")){
                // port de la database
                this.portsFournis.get(2).setInformation(requete);
            }else{
                // port du serveur details
                this.portsFournis.get(0).setInformation(requete);
            }

        } else {
            //port du serveur detail
            this.portsFournis.get(0).setInformation(requete);
        }
    }
}
