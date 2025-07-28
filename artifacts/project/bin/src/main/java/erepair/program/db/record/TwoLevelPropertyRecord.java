package erepair.program.db.record;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * This class represents a two-level property record
 *
 **/
public abstract class TwoLevelPropertyRecord<T extends Serializable, U extends Serializable, V extends Serializable> extends PropertyRecord<T, PropertyRecord<U, V>> {

    public TwoLevelPropertyRecord(Supplier<PropertyRecord<U, V>> defaultValue) {
        super(defaultValue);
    }

    @Override
    Object getJsonValue(PropertyRecord<U, V> value) {
        final JSONObject object = new JSONObject();
        for (var entry : value.entrySet()) {
            final String key = value.getJsonKey(entry.getKey());
            final Object val = value.getJsonValue(entry.getValue());
            object.put(key, val);
        }
        return object;
    }

    /**
     * Instantiate a new child
     *
     * @param parent Parent of the child
     * @return a new child instance
     */
    abstract PropertyRecord<U, V> instantiate(T parent);

    @Override
    PropertyRecord<U, V> fromJsonValue(String key, JSONObject source) {
        final JSONObject object = source.getJSONObject(key);
        final PropertyRecord<U, V> child = instantiate(this.fromJsonKey(key, source));
        child.fromJSON(object);
        return child;
    }

}
