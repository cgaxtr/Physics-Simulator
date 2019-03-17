package simulator.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PhysicsSimulator {

    private GravityLaws law;
    private double dt;
    private List<Body> bodies;
    private double realTime;
    private List<SimulatorObserver> observers;


    public PhysicsSimulator(GravityLaws law, double time){
        if (law == null)
            throw new IllegalArgumentException("Not a valid law");
        if (time < 0)
            throw new IllegalArgumentException("Not valid time value");

        this.law = law;
        this.dt = time;
        bodies = new ArrayList<>();
        this.realTime = 0;
        observers = new ArrayList<>();
    }

    public void addBody(Body b){
        boolean found = false;
        int i = 0;

        while (i < bodies.size()-1 && !found){
            if (bodies.get(i).equals(b))
                found = true;
            else
                i++;
        }

        if (!found)
            bodies.add(b);
        else throw new IllegalArgumentException("Body already exists");

        for (SimulatorObserver o : observers)
            o.onBodyAdded(bodies, b);
    }

    public void advance(){
        law.apply(bodies);

        for(Body b : bodies){
            b.move(dt);
        }

        realTime += dt;

        for (SimulatorObserver o : observers){
            o.onAdvance(bodies, realTime);
        }
    }

    public void reset() {
        bodies.clear();
        dt = 0.0;

        for(SimulatorObserver o: observers)
            o.onReset(bodies, realTime, dt, law.toString());
    }

    public void setDeltaTime(double dt) {
        if(dt < 0)
            throw new IllegalArgumentException("Not valid delta-time");

        this.dt = dt;

        for(SimulatorObserver o: observers)
            o.onDeltaTimeChanged(dt);
    }

    public void setGravityLaws(GravityLaws gravityLaws) {
        if (gravityLaws == null)
            throw new IllegalArgumentException("Not a valid gravity law");

        this.law = gravityLaws;

        for(SimulatorObserver o: observers)
            o.onGravityLawChanged(law.toString());
    }

    public void addObserver(SimulatorObserver o) {
        boolean found = false;
        Iterator<SimulatorObserver> it = observers.iterator();

        while(it.hasNext() && !found){
            if(it.next().equals(o))
                found = true;
        }

        if (!found) {
            observers.add(o);
            o.onRegister(bodies,realTime,dt,law.toString());
        }

    }

    public String toString(){
        return  "{ \"time\": " + realTime + ", \"bodies\": " + bodies.toString() + " }";
    }
}
