package erepair.program.db.record;

import org.json.JSONObject;

/**
 **/
public class AlgorithmBooleanPropertyRecord extends AlgorithmPropertyRecord<Boolean> {
    public AlgorithmBooleanPropertyRecord(boolean defaultValue) {
        super(() -> defaultValue);
    }

    @Override
    Object getJsonValue(Boolean value) {
        return value;
    }

    @Override
    Boolean fromJsonValue(String key, JSONObject source) {
        return source.getBoolean(key);
    }
}
