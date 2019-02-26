package simulator.control;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

import java.io.*;

public class Controller {

    private PhysicsSimulator ps;
    private Factory<Body> factory;

    public Controller(PhysicsSimulator physicsSimulator, Factory<Body> factory){
        this.ps = physicsSimulator;
        this.factory = factory;

    }

    public void run(int steps){
        System.out.println(execute(steps));
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
            ps.addBody(factory.createInstance(ja.getJSONObject(i)));
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
