package M2.Interface;

import M2.ObjectArchi.ObjetArchitectural;

import java.io.ObjectInputStream;

/**
 * Created by Abdeldjallil on 17/10/2016.
 */
public class Role extends Interface {

    public Role(ObjetArchitectural objetArchi, String name) {
        super(objetArchi, name);
    }

    public void setInformation(ObjetArchitectural emetteur, String information){}


}
