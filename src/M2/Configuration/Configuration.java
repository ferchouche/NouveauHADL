package M2.Configuration;

import M2.Composant.ComposantAbstrait;
import M2.Composant.ComposantConcret;
import M2.Connecteur.ConnecteurAbstrait;
import M2.Connecteur.ConnecteurConcret;
import M2.Connecteur.ConnecteurExplicite;
import M2.Connecteur.LienAttachement;
import M2.Interface.*;

import java.util.LinkedList;

/**
 * Created by Abdeldjallil on 17/10/2016.
 */
public abstract class Configuration extends ConnecteurExplicite implements ComposantAbstrait  {
    protected LinkedList<ComposantAbstrait> composants;
    protected LinkedList<ConnecteurAbstrait> connecteurs;
    protected LinkedList<PortConfigurationFourni> portsConfigFournis;
    protected LinkedList<PortConfigurationRequis> portsConfigRequises;
    protected String name;

    public Configuration(String name) {
        this.name = name;
        this.composants = new LinkedList<>();
        this.connecteurs = new LinkedList<>();
        this.portsConfigFournis = new LinkedList<>();
        this.portsConfigRequises = new LinkedList<>();
    }

    public void ajouterComposant(ComposantConcret composantConcret){
        composants.add(composantConcret);
    }

    public void ajouterConnecteur(ConnecteurConcret connecteurConcret){
        connecteurs.add(connecteurConcret);
    }
}
