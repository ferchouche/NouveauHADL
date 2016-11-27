package M2.Connecteur;

import M1.Interface.*;
import M1.ServerDetail.ConnectionManager;
import M1.Serveur.Serveur;
import M1.Serveur.ServeurDetail;
import M2.Interface.*;
import M2.Interface.PortComposantFourni;
import M2.Interface.PortComposantRequis;
import M2.Interface.PortConfigurationFourni;
import M2.Interface.PortConfigurationRequis;
import M2.ObjectArchi.ObjetArchitectural;

/**
 * Created by Abdeldjallil on 17/10/2016.
 */
public class LienBinding implements ConnecteurAbstrait {
    public PortComposant portComposant;
    public PortConfiguration portConfiguration;

    public LienBinding(PortComposantFourni portComposantFourni, PortConfigurationFourni portConfigurationFourni){
        portComposant = portComposantFourni;
        portConfiguration = portConfigurationFourni;
    }

    public LienBinding(PortComposantRequis portComposantRequis, PortConfigurationRequis portConfigurationRequis){
        portComposant = portComposantRequis;
        portConfiguration = portConfigurationRequis;
    }


    public void transmettre(Interface emetteur){
        if (emetteur.equals(portComposant)){
            portConfiguration.setInformation(portComposant.getInformation());
        }else{
            portComposant.setInformation(portConfiguration.getInformation());
        }
    }

    public void TransmettreVerConfig(){
        this.portConfiguration.setInformation(portComposant.getInformation());
    }

    public void TransmetterVersComposant() {
        this.portComposant.setInformation(portConfiguration.getInformation());
        //System.out.printf("La\n");
    }


}
