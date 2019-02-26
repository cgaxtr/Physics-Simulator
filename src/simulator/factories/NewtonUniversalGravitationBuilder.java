package simulator.factories;

import org.json.JSONObject;
import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws> {

    private static final String TAG = "nlug";
    private static final String DESC = "Newton’s law of universal gravitation";

    public NewtonUniversalGravitationBuilder(){
        super.desc = DESC;
        super.typeTag = TAG;
    }

    @Override
    public GravityLaws createTheInstance(JSONObject jsonObject) {
        return new NewtonUniversalGravitation();
    }
}
