package erepair.program.db;

import org.json.JSONObject;

/**
 **/
public interface JSONSerializable {
    JSONObject toJSON();

    void fromJSON(JSONObject jsonObject);
}
