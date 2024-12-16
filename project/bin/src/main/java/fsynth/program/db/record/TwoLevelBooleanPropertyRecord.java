package fsynth.program.db.record;

import fsynth.program.subject.Subjects;
import fsynth.program.Algorithm;
import org.json.JSONObject;

/**
 * @author Lukas Kirschner
 * @since 2020-05-07
 **/
public class TwoLevelBooleanPropertyRecord extends TwoLevelPropertyRecord<Subjects, Algorithm, Boolean> {

    public TwoLevelBooleanPropertyRecord(boolean defaultValue) {
        super(() -> new AlgorithmBooleanPropertyRecord(defaultValue));
    }

    @Override
    PropertyRecord<Algorithm, Boolean> instantiate(Subjects parent) {
        return super.defaultValue.get();
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
