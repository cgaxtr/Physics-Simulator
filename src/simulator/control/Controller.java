package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

import java.io.*;

public class Controller {

    private PhysicsSimulator ps;
    private Factory<Body> bodyFactory;
    private Factory<GravityLaws> lawsFactory;

    public Controller(PhysicsSimulator physicsSimulator, Factory<Body> bodyFactory, Factory<GravityLaws> lawsFactory){
        this.ps = physicsSimulator;
        this.bodyFactory = bodyFactory;
        this.lawsFactory = lawsFactory;

    }

    public void run(int steps){
        execute(steps);
    }

    public void run(int steps, OutputStream outStream) throws IOException {
        byte[] s = execute(steps).getBytes();

        outStream.write(s);
    }

    public void loadBodies(InputStream inStream){
        JSONObject jsonObject;
        JSONArray ja;

        jsonObject = new JSONObject(new JSONTokener(inStream));
        ja = jsonObject.getJSONArray("bodies");

        for (int i = 0; i < ja.length(); i++) {
            ps.addBody(bodyFactory.createInstance(ja.getJSONObject(i)));
        }

    }

    public void reset() {
        ps.reset();
    }

    public void setDeltaTime(double dt) {
        ps.setDeltaTime(dt);
    }

    public void addObserver(SimulatorObserver o) {
        ps.addObserver(o);
    }

    public Factory<GravityLaws> getGravityLawsFactory() {
        return lawsFactory;
    }

    public void setGravityLaws(JSONObject info) {
        GravityLaws gl;
        //todo check this
        if ((gl = lawsFactory.createInstance(info)) != null){
            ps.setGravityLaws(gl);
        }

    }


    private String execute(int steps){
        StringBuilder sb = new StringBuilder();
        sb.append("{\n\"states\": [\n");
        sb.append(ps.toString());

        for (int i = 0; i < steps; i++){
            ps.advance();
            sb.append(",");
            sb.append("\n");
            sb.append(ps.toString());
        }
        sb.append("\n]\n}\n");

        return sb.toString();
    }
}
