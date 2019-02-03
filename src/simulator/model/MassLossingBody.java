package simulator.model;

import simulator.misc.Vector;

public class MassLossingBody extends Body {

    private double lossFactor;
    private double lossFrequency;

    public MassLossingBody(String id, Vector a, Vector v, Vector p, double m, double lossFactor, double lossFrequency){
        super(id,a,v, p, m);

        this.lossFactor = lossFactor;
        this.lossFrequency = lossFrequency;
    }

    public void move(double t){

    }
}
