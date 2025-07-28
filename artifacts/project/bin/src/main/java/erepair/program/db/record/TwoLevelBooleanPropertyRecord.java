package erepair.program.db.record;

import erepair.program.subject.Subjects;
import erepair.program.Algorithm;
import org.json.JSONObject;

/**
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
