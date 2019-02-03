package simulator.model;

import simulator.misc.Vector;

public class Body {

    private String id;
    private Vector velocity;
    private Vector acceleration;
    private Vector possition;
    private double mass;

    Body(String id, Vector v, Vector a, Vector p, double m){
        this.id = id;
        velocity = v;
        acceleration = a;
        possition = p;
        mass = m;
    }

    public String getId(){
        return id;
    }

    public Vector getVelocity(){
        return velocity;
    }

    public Vector getAcceleration(){
        return acceleration;
    }

    public Vector getPosition(){
        return null;
    }

    double getMass(){
        return mass;
    }

    void setVelocity(Vector v){
        velocity = v;
    }

    void setAcceleration(Vector a){
        acceleration = a;
    }

    void move(double t){
        //move obejct during t seconds
    }

    public String toString(){

        //return "{ \"id\": " + id "}";

        return null;
    }
}
