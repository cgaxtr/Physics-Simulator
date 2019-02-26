package simulator.factories;

import org.json.JSONObject;
import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder extends Builder<GravityLaws> {

    private static final String TAG = "ftcg";
    private static final String DESC = "Falling to center gravity";

    public FallingToCenterGravityBuilder(){
        super.desc = DESC;
        super.typeTag = TAG;
    }

    @Override
    public GravityLaws createTheInstance(JSONObject jsonObject) {
        return new FallingToCenterGravity();
    }
}
