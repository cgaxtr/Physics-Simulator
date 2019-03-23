package simulator.model;

import java.util.List;

public class FallingToCenterGravity implements GravityLaws {

    public static final double GRAVITY = 9.81;

    @Override
    public void apply(List<Body> bodies) {
        for(Body b : bodies){
            b.setAcceleration(b.getPosition().direction().scale(-GRAVITY));
        }
    }

    public String toString(){
        return "Falling To The Center Gravity";
    }
}
