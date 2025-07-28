package erepair.program.db.record;

import org.json.JSONObject;

/**
 **/
public class AlgorithmStringPropertyRecord extends AlgorithmPropertyRecord<String> {
    AlgorithmStringPropertyRecord(String defaultValue) {
        super(() -> defaultValue);
    }

    @Override
    Object getJsonValue(String value) {
        return value;
    }

    @Override
    String fromJsonValue(String key, JSONObject source) {
        return source.getString(key);
    }
}
