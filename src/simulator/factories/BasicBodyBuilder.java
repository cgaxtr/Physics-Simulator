package simulator.factories;

import org.json.JSONObject;
import simulator.misc.Vector;
import simulator.model.Body;


public class BasicBodyBuilder extends Builder<Body> {

    public static final String DESC= "basic body";
    public static final String TAG = "basic";


    public BasicBodyBuilder(){
        super.desc = DESC;
        super.typeTag = TAG;
    }

    @Override
    protected Body createTheInstance(JSONObject jsonObject) {
        Body b;

        Vector pos = new Vector(jsonArrayToDoubleArray(jsonObject.getJSONArray("pos")));
        Vector vel = new Vector(jsonArrayToDoubleArray(jsonObject.getJSONArray("vel")));
        Vector ace = new Vector(vel.dim());

        b = new Body(jsonObject.getString("id"), vel, ace, pos, jsonObject.getDouble("mass"));

        return b;
    }

    public JSONObject createData(){

        return new JSONObject(new Body("id", new Vector(0), new Vector(0), new Vector(0), 0.0).toString());
    }
}
