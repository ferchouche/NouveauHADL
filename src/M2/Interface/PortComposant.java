package M2.Interface;

import M2.ObjectArchi.ObjetArchitectural;

/**
 * Created by Abdeldjallil on 17/10/2016.
 */
public abstract class PortComposant extends Port {

    public PortComposant(ObjetArchitectural objetArchi, String name) {
        super(objetArchi, name);
    }
}
