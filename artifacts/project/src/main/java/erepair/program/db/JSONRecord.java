package erepair.program.db;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class describes a JSON-Serializable object that consists of sub-objects. The root object is a JSON Array
 * and each field can automatically be serialized in subclasses by adding the {@link JSONField} annotation.
 *
 **/
public abstract class JSONRecord {

    final SortedSet<Field> jsonFields;

    public JSONRecord() {
        jsonFields = new TreeSet<>(Comparator.comparingInt(field -> field.getAnnotation(JSONField.class).index()));
        for (Field f : this.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(JSONField.class)) {
                jsonFields.add(f);
            }
        }
    }

    void loadFromJSON(JSONArray jsonArray) throws IllegalAccessException {
        int index = 0;
        var it = jsonFields.iterator();
        while (it.hasNext()) {
            final Field field = it.next();
            final JSONSerializable target = (JSONSerializable) field.get(this);
            if (jsonArray.length() <= index) { // Assuming a database from a previous version
                //throw new IllegalAccessException("The Database Array was too short, length: " + jsonArray.length() + ", expected: " + jsonFields.size());
                target.fromJSON(new JSONObject());
            } else {
                try {
                    target.fromJSON(jsonArray.getJSONObject(index++));
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    throw new IllegalAccessException(e.toString());
                }
            }
        }
    }

    public void storeToJSON(JSONArray object) throws IllegalAccessException {
        var it = jsonFields.iterator();
        while (it.hasNext()) {
            final Field field = it.next();
            try {
                final JSONSerializable target = (JSONSerializable) field.get(this);
                object.put(target.toJSON());
            } catch (ClassCastException e) {
                e.printStackTrace();
                throw new IllegalAccessException(e.toString());
            }
        }
    }
}
