package fsynth.program.db.record;

import org.json.JSONObject;

/**
 * @author Lukas Kirschner
 * @since 2020-05-07
 **/
public class AlgorithmLongPropertyRecord extends AlgorithmPropertyRecord<Long> {
    AlgorithmLongPropertyRecord(long defaultValue) {
        super(() -> defaultValue);
    }

    @Override
    Object getJsonValue(Long value) {
        return value;
    }

    @Override
    Long fromJsonValue(String key, JSONObject source) {
        return source.getLong(key);
    }
}
