package erepair.program.db.record;

import org.json.JSONObject;

/**
 **/
public class StringLongPropertyRecord extends PropertyRecord<String, Long> {
    public StringLongPropertyRecord(long defaultValue) {
        super(() -> defaultValue);
    }

    @Override
    String getJsonKey(String key) {
        return key;
    }

    @Override
    String fromJsonKey(String key, JSONObject source) {
        return key;
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
