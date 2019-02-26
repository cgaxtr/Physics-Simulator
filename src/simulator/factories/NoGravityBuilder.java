package simulator.factories;

import org.json.JSONObject;
import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder<GravityLaws>{

    private static final String TAG = "ng";
    private static final String DESC = "No gravity";

    public NoGravityBuilder(){
        super.desc = DESC;
        super.typeTag = TAG;
    }

    @Override
    public GravityLaws createTheInstance(JSONObject jsonObject) {
        return new NoGravity();
    }
}
