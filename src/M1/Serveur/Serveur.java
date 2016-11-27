package M1.Serveur;

import M1.Interface.PortComposantFourni;
import M1.Interface.PortComposantRequis;
import M1.Systeme.SystemeCS;
import M2.Composant.ComposantConcret;
import M2.Connecteur.LienBinding;
import M2.Interface.Interface;
import M2.Interface.PortConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abdeldjallil on 23/10/2016.
 */
public class Serveur extends ComposantConcret{
    SystemeCS cs;
    ServeurDetail sd;
    public Map<Interface, LienBinding> bindingMap;
    public Serveur(SystemeCS cs) {
        super("Server");
        bindingMap = new HashMap<>();
        this.portsRequis.add(new PortComposantRequis(this, "PortServeurRequis"));
        this.portsFournis.add(new PortComposantFourni(this, "PortServeurFourni"));
        this.sd = new ServeurDetail(this);
        this.cs = cs;

        bindingMap.put(this.getPremierRequis(), //port requis serveur
                new LienBinding(this.getPremierRequis(), //port requisserveur vers serveurdetail
                        sd.getPremierRequis())); // port requis serveurDetail

        bindingMap.put(sd.getPremierFourni(), //port fourni serveurDetail
                new LienBinding(this.getPremierFourni(), //port fourni serveurDetail vers serveur
                        sd.getPremierFourni())); //port fourni serveur
    }

    public PortComposantFourni getPremierFourni(){
        return (PortComposantFourni)portsFournis.getFirst(); // without cast, function returning M2 PortComposantFourni
    }

    public PortComposantRequis getPremierRequis(){
        return (PortComposantRequis)portsRequis.getFirst(); // without cast, function returning M2 PortComposantRequis
    }

    public void notifierSystem(Interface notifieur){
        if (notifieur instanceof PortComposantRequis){
            this.notificationBind(notifieur);}
        else{
            cs.notification(this.portsFournis.getFirst(), this);
        }
    }
    public void notificationBind(Interface interfaceCalling) {

        if (interfaceCalling instanceof PortConfiguration)
            bindingMap.get(interfaceCalling).TransmetterVersComposant();
        else
            bindingMap.get(interfaceCalling).TransmettreVerConfig();
    }

}
