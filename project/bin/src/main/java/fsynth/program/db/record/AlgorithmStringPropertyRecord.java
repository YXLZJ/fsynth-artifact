package fsynth.program.db.record;

import org.json.JSONObject;

/**
 * @author Lukas Kirschner
 * @since 2020-05-07
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
