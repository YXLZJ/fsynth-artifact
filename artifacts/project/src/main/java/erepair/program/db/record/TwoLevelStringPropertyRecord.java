package erepair.program.db.record;

import erepair.program.subject.Subjects;
import erepair.program.Algorithm;
import org.json.JSONObject;

/**
 **/
public class TwoLevelStringPropertyRecord extends TwoLevelPropertyRecord<Subjects, Algorithm, String> {

    public TwoLevelStringPropertyRecord(String defaultValue) {
        super(() -> new AlgorithmStringPropertyRecord(defaultValue));
    }

    @Override
    PropertyRecord<Algorithm, String> instantiate(Subjects parent) {
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
