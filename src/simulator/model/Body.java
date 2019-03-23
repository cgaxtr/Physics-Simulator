package simulator.model;

import simulator.misc.Vector;

public class Body {

    protected String id;
    protected Vector velocity;
    protected Vector acceleration;
    protected Vector position;
    protected double mass;

    public Body(String id, Vector v, Vector a, Vector p, double m){
        this.id = id;
        velocity = v;
        acceleration = a;
        position = p;
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
        return position;
    }

    public double getMass(){
        return mass;
    }

    void setVelocity(Vector v){
        velocity = new Vector(v);
    }

    void setAcceleration(Vector a){
        acceleration = new Vector(a);
    }

    void setPosition(Vector p){
        acceleration = new Vector(p);
    }

    void move(double t){
        Vector vt = velocity.scale(t);
        Vector at2 = acceleration.scale(0.5).scale(Math.pow(t,2.0));

        position = position.plus(vt).plus(at2);
        velocity = acceleration.scale(t).plus(velocity);
    }

    public boolean equals(Body b){

        return this.id.equals(b.getId());
    }

    public String toString(){

        return " {  \"id\": \"" + id + "\", \"mass\": " + mass + ", \"pos\": " + position.toString() + ", \"vel\": " + velocity.toString() + ", \"acc\": " + acceleration.toString() + " } ";
    }
}
