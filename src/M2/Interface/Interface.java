package M2.Interface;

import M2.ObjectArchi.ObjetArchitectural;

/**
 * Created by Abdeldjallil on 19/10/2016.
 */
public abstract class Interface {
    public ObjetArchitectural parent;
    protected String information;
    protected String name;

    public Interface(ObjetArchitectural objetArchi, String name){
        this.parent = objetArchi;
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getName() {
        return name;
    }
}
