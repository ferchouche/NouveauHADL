package M1.Serveur;

import M1.Client.Client;
import M1.Connecteur.ClearanceRequest;
import M1.Connecteur.RPC;
import M1.Connecteur.SQL_Query;
import M1.Connecteur.SecurityQuery;
import M1.Interface.PortComposantFourni;
import M1.Interface.PortComposantRequis;
import M1.Interface.PortConfigurationFourni;
import M1.Interface.PortConfigurationRequis;
import M1.ServerDetail.ConnectionManager;
import M1.ServerDetail.DataBase;
import M1.ServerDetail.SecurityManager;
import M1.Systeme.SystemeCS;
import M2.Configuration.Configuration;
import M2.Connecteur.LienAttachement;
import M2.Connecteur.LienBinding;
import M2.Interface.Interface;
import M2.Interface.PortConfiguration;
import M2.ObjectArchi.ObjetArchitectural;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abdeldjallil on 23/10/2016.
 */
public class ServeurDetail extends Configuration{

    public Serveur serveur;

    private Map<Interface, LienAttachement> attachementMap;
    private Map<Interface, LienBinding> bindingMap;

    public ServeurDetail(Serveur serveur) {
        super("Serveur Detail");
        attachementMap = new HashMap<>();
        bindingMap = new HashMap<>();
        this.portsConfigFournis.add(new PortConfigurationFourni(this, "SendResponseServeurDetail"));
        this.portsConfigRequises.add(new PortConfigurationRequis(this, "ReceiveRequestServeurDetail"));
        this.composants.add(new ConnectionManager(this));
        this.composants.add(new SecurityManager(this));
        this.composants.add(new DataBase(this));
        this.connecteurs.add(new ClearanceRequest(this));
        this.connecteurs.add(new SecurityQuery(this));
        this.connecteurs.add(new SQL_Query(this));
        //ajouter les connecteurs
        this.serveur = serveur;

        //ce code pour relier les ports fournis et requis security_check_Requis du connection manager avec le connecteur  ClearanceRequest
        attachementMap.put(((ConnectionManager)composants.getFirst()).getFourni(1), // port fourni ConnectionManager
                new LienAttachement(((ConnectionManager)composants.getFirst()).getFourni(1), // port fourni ConnectionManager
                        ((ClearanceRequest)connecteurs.getFirst()).getRole(0))); // role requis ClearanceRequest

        attachementMap.put(((ClearanceRequest)connecteurs.getFirst()).getRole(1), // role fourni ClearanceRequest
                new LienAttachement(((ConnectionManager)composants.getFirst()).getRequis(1), // port requis ConnectionManager
                        ((ClearanceRequest)connecteurs.getFirst()).getRole(1))); // role fourni ClearanceRequest

        //ce code pour relier les ports fournis et requis security_auth_Requis du SecurityManager avec le connecteur  ClearanceRequest
        attachementMap.put(((SecurityManager)composants.get(1)).getFourni(0),
                new LienAttachement(((SecurityManager)composants.get(1)).getFourni(0),
                        ((ClearanceRequest)connecteurs.getFirst()).getRole(0)));

        attachementMap.put(((ClearanceRequest)connecteurs.getFirst()).getRole(2),
                new LienAttachement(((SecurityManager)composants.get(1)).getRequis(0),
                        ((ClearanceRequest)connecteurs.getFirst()).getRole(2)));

        //ce code pour relier les ports fournis et requis check_query du SecurityManager avec le connecteur  SecurityQuery
        attachementMap.put(((SecurityManager)composants.get(1)).getFourni(1),
                new LienAttachement(((SecurityManager)composants.get(1)).getFourni(1),
                        ((SecurityQuery)connecteurs.get(1)).getRole(0)));

        attachementMap.put(((SecurityQuery)connecteurs.get(1)).getRole(1),
                new LienAttachement(((SecurityManager)composants.get(1)).getRequis(1),
                        ((SecurityQuery)connecteurs.get(1)).getRole(1)));

        //ce code pour relier les ports fournis et requis check_query du DataBase avec le connecteur  SecurityQuery
        attachementMap.put(((DataBase)composants.get(2)).getFourni(1),
                new LienAttachement(((DataBase)composants.get(2)).getFourni(1),
                        ((SecurityQuery)connecteurs.get(1)).getRole(0)));

        attachementMap.put(((SecurityQuery)connecteurs.get(1)).getRole(2),
                new LienAttachement(((DataBase)composants.get(2)).getRequis(1),
                        ((SecurityQuery)connecteurs.get(1)).getRole(2)));

        //ce code pour relier les ports fournis et requis dbQuery du connection manager avec le connecteur  SQL_Query
        attachementMap.put(((ConnectionManager)composants.getFirst()).getFourni(2),
                new LienAttachement(((ConnectionManager)composants.getFirst()).getFourni(2),
                        ((SQL_Query)connecteurs.get(2)).getRole(0)));

        attachementMap.put(((SQL_Query)connecteurs.get(2)).getRole(1),
                new LienAttachement(((ConnectionManager)composants.getFirst()).getRequis(2),
                        ((SQL_Query)connecteurs.get(2)).getRole(1)));

        //ce code pour relier les ports fournis et requis check_query du DataBase avec le connecteur  SecurityQuery
        attachementMap.put(((DataBase)composants.get(2)).getFourni(0),
                new LienAttachement(((DataBase)composants.get(2)).getFourni(0),
                        ((SQL_Query)connecteurs.get(2)).getRole(0)));

        attachementMap.put(((SQL_Query)connecteurs.get(2)).getRole(2),
                new LienAttachement(((DataBase)composants.get(2)).getRequis(0),
                        ((SQL_Query)connecteurs.get(2)).getRole(2)));
        
        bindingMap.put((this).getPremierRequis(),
                new LienBinding(((ConnectionManager)composants.get(0)).getRequis(0),
                        (this.getPremierRequis())));
        bindingMap.put(((ConnectionManager)composants.get(0)).getFourni(0),
                new LienBinding(((ConnectionManager)composants.get(0)).getFourni(0),
                        (this.getPremierFourni())));
    }

    public PortConfigurationFourni getPremierFourni(){
        return (PortConfigurationFourni)portsConfigFournis.getFirst(); // without cast, function returning M2 PortComposantFourni
    }

    public PortConfigurationRequis getPremierRequis(){
        return (PortConfigurationRequis)portsConfigRequises.getFirst(); // without cast, function returning M2 PortComposantRequis
    }

    public void notification(Interface interfaceCalling, ObjetArchitectural emetteur){
        attachementMap.get(interfaceCalling).transmettre(emetteur);
    }

    public void notification(Interface interfaceCalling) {
       // System.out.printf("La configue appel le lien d'attachement entre le ClearanceRequest et le composant\n");
        attachementMap.get(interfaceCalling).transmettre();
    }

    public void notifierBinding(Interface notifieur){
        if (notifieur instanceof PortConfiguration)
            this.bindingMap.get(notifieur).TransmetterVersComposant();
        else
            this.bindingMap.get(notifieur).TransmettreVerConfig();
    }

    public void Notification() {

        this.serveur.notificationBind(this.getPremierFourni());
    }

    public void testerrrr() {

        this.getPremierRequis().setInformation("cccc");

    }
}
