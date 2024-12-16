package fsynth.program.db.record;

import fsynth.program.subject.Subjects;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * This class represents a property record that maps a subject to a type T
 *
 * @author Lukas Kirschner
 * @since 2020-05-07
 **/
public abstract class SubjectPropertyRecord<T extends Serializable> extends PropertyRecord<Subjects, T> {
    SubjectPropertyRecord(Supplier<T> defaultValue) {
        super(defaultValue);
    }

    @Override
    String getJsonKey(Subjects key) {
        return key.getJsonKey();
    }

    @Override
    Subjects fromJsonKey(String key, JSONObject source) {
        return Subjects.fromJSONKey(key);
    }
}
