package simulator.model;

import simulator.misc.Vector;

import java.util.List;

public class NewtonUniversalGravitation implements GravityLaws {

    public static final double G = 6.67E-11;

    @Override
    public void apply(List<Body> bodies) {
        for (int i = 0; i < bodies.size(); i++){
            double fij;
            Vector Fij = new Vector(bodies.get(i).getAcceleration().dim());
            for (int j = 0; j < bodies.size(); j++){
                if (i != j){
                    fij = (G * bodies.get(i).getMass() * bodies.get(j).getMass()) / (Math.pow(bodies.get(j).getPosition().distanceTo(bodies.get(i).getPosition()),2));
                    Fij = Fij.plus((bodies.get(j).getPosition().minus(bodies.get(i).getPosition())).direction().scale(fij));

                }
            }
            bodies.get(i).setAcceleration(Fij.scale(1.0/bodies.get(i).getMass()));
        }
    }
}
