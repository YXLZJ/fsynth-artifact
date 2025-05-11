package erepair.program.db.record;

import org.json.JSONObject;

/**
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
