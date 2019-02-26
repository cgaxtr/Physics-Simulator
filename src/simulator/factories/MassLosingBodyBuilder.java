package simulator.factories;

import org.json.JSONObject;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder<Body> {

    private static final String TAG = "mlb";
    private static final String DESC = "Mass lossing body";

    public MassLosingBodyBuilder(){
        super.desc = DESC;
        super.typeTag = TAG;
    }

    @Override
    public Body createTheInstance(JSONObject jsonObject) {

        Vector vel = new Vector(jsonArrayToDoubleArray(jsonObject.getJSONArray("vel")));
        Vector pos = new Vector(jsonArrayToDoubleArray(jsonObject.getJSONArray("pos")));
        Vector ace = new Vector(vel.dim());

        return new MassLossingBody(jsonObject.getString("id"), ace, vel, pos, jsonObject.getDouble("mass"),
                jsonObject.getDouble("factor"), jsonObject.getDouble("freq"));

    }

    public JSONObject createData(){
        return new JSONObject(new MassLossingBody("id", new Vector(0), new Vector(0), new Vector(0), 0.0, 0.0 , 0.0).toString());
    }
}
