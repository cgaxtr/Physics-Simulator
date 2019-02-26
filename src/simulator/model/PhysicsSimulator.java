package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSimulator {

    private GravityLaws law;
    private double dt;
    private List<Body> list;
    private double realTime;


    public PhysicsSimulator(GravityLaws law, double time){
        if (law == null)
            throw new IllegalArgumentException("Not a valid law");
        if (time < 0)
            throw new IllegalArgumentException("Not valid time value");

        this.law = law;
        this.dt = time;
        list = new ArrayList<>();
        this.realTime = 0;
    }

    public void addBody(Body b){
        boolean found = false;
        int i = 0;

        //todo exception
        while (i < list.size()-1 && !found){
            if (list.get(i).equals(b))
                found = true;
            else
                i++;
        }

        if (!found)
            list.add(b);
    }

    public void advance(){
        law.apply(list);

        for(Body b : list){
            b.move(dt);
        }

        realTime += dt;
    }

    public String toString(){
        return  "{ \"time\": " + realTime + ", \"bodies\": " + list.toString() + " }";
    }
}
