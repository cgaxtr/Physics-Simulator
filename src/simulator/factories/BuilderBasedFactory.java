package simulator.factories;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BuilderBasedFactory<T> implements Factory{

    private List<Builder<T>> list;

    public BuilderBasedFactory(List<Builder<T>> list) {
        this.list = list;
    }

    @Override
    public T createInstance(JSONObject info) throws IllegalArgumentException{
        T o;

        for(Builder<T> b : list){
            if ((o = b.createInstance(info)) != null)
                return o;
        }

        throw new IllegalArgumentException("Invalid object");
    }

    @Override
    public List<JSONObject> getInfo() {
        List<JSONObject> returnList = new ArrayList<>();

        for(Builder b : list){
            returnList.add(b.getBuilderInfo());
        }
        return returnList;
    }
}
