package simulator.model;

import simulator.misc.Vector;

public class MassLossingBody extends Body {

    private double lossFactor;
    private double lossFrequency;
    private double timer;

    public MassLossingBody(String id, Vector a, Vector v, Vector p, double m, double lossFactor, double lossFrequency){
        super(id, v, a, p, m);

        this.lossFactor = lossFactor;
        this.lossFrequency = lossFrequency;
        timer = 0.0;
    }

    public void move(double t){
        super.move(t);

        timer += t;
        if (timer >= lossFrequency) {
            mass = mass * (1 - lossFactor);
            timer = 0.0;
        }
    }
}
