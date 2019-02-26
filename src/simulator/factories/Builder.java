package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Builder<T> {

    protected String typeTag;
    protected String desc;

    protected double[] jsonArrayToDoubleArray(JSONArray jsonArray){
        double[] array = new double[jsonArray.length()];

        for(int i = 0; i < jsonArray.length(); i++){
            array[i] = jsonArray.getDouble(i);
        }

        return array;
    }

    public T createInstance(JSONObject info){

        if (info.get("type").equals(typeTag))
            return createTheInstance(info.getJSONObject("data"));

        return null;

    }

    public JSONObject getBuilderInfo(){
        JSONObject js = new JSONObject();

        js.put("type", typeTag);
        js.put("desc", desc);
        js.put("data",createData());

        return js;
    }

    protected JSONObject createData(){
        JSONObject js = new JSONObject();

        return js;
    }

    protected abstract T createTheInstance(JSONObject jsonObject);

}
