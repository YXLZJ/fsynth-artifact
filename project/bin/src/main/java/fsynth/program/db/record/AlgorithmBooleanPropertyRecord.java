package fsynth.program.db.record;

import org.json.JSONObject;

/**
 * @author Lukas Kirschner
 * @since 2020-05-07
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
