package fsynth.program.db.record;

import fsynth.program.db.JSONSerializable;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * PropertyRecord to store properties in a FileRecord.
 *
 * @param <T> Type of the key
 * @param <U> Type of the value
 */
public abstract class PropertyRecord<T extends Serializable, U extends Serializable> extends HashMap<T, U> implements Serializable, JSONSerializable {
    final Supplier<U> defaultValue;

    public PropertyRecord(Supplier<U> defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Optional<U> sum(BiFunction<U, U, U> function) {
        return super.entrySet().stream().map(Entry::getValue).reduce(function::apply);
    }

    public long sumToLong(ToLongFunction<U> converter) {
        return super.values().stream().mapToLong(converter).sum();
    }

    public int sumToInt(ToIntFunction<U> converter) {
        return super.values().stream().mapToInt(converter).sum();
    }

    /**
     * Converts the data content of this instance to a {@link JSONObject} for saving the whole content into a JSON file
     *
     * @return the generated JSON Object
     */
    @Override
    public final JSONObject toJSON() {
        JSONObject ret = new JSONObject();
        for (Entry<T, U> entry : this.entrySet()) {
            ret.put(this.getJsonKey(entry.getKey()), this.getJsonValue(entry.getValue()));
        }
        return ret;
    }

    /**
     * Converts a key of this map into a JSON key that describes the property
     *
     * @param key Key to convert
     * @return the JSON String value
     */
    abstract String getJsonKey(T key);

    /**
     * Convert the key of a JSON Object into a key of this property record.
     *
     * @param key    JSON Key, given as String
     * @param source JSON Object to read the key from
     * @return the property key
     */
    abstract T fromJsonKey(String key, JSONObject source);

    /**
     * Convert the given value into a JSON object that can be stored into a {@link JSONObject}.
     * For a list of available object types, see the respective JavaDoc: {@link JSONObject}
     *
     * @param value Value to convert
     * @return the JSON Object
     */
    abstract Object getJsonValue(U value);

    /**
     * Read the JSON Value from a JSON Object and return the whole object as property record
     *
     * @param key    Key in the JSON Object
     * @param source Source object to read the property from
     * @return the property
     */
    abstract U fromJsonValue(String key, JSONObject source);

    /**
     * Fills this PropertyRecord with data from a {@link JSONObject}
     *
     * @param jsonObject Data Source
     */
    @Override
    public final void fromJSON(JSONObject jsonObject) {
        for (String val : jsonObject.keySet()) {
            final T key = this.fromJsonKey(val, jsonObject);
            final U value = this.fromJsonValue(val, jsonObject);
            this.put(key, value);
        }
    }


    /**
     * Gets the value associated with {@param key} or the result of defaultValue, if element could not be found.
     * Does not put anything into the HashMap.
     *
     * @param key Key to lookup
     * @return Result of get, or new instance
     */
    @Override
    public U get(Object key) {
        if (super.containsKey(key)) {
            return super.get(key);
        } else {
            return defaultValue.get();
        }
    }

    /**
     * Puts the result of defaultValue into the HashMap, if nothing is associated with {@param key} and returns
     * the result of {@link PropertyRecord#get(Object)}
     *
     * @param key Key to lookup
     * @return Result of {get}
     */
    public U getAndPut(T key) {
        if (!super.containsKey(key)) {
            super.put(key, defaultValue.get());
        }
        return super.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.forEach((key, value) -> sb.append(key.toString())
                .append(": ")
                .append(value != null ? value.toString() : "NULL")
                .append("; "));
        return sb.toString();
    }
}
