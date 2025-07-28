package erepair.program.db.record;

import erepair.program.subject.Subjects;
import erepair.program.Algorithm;
import org.json.JSONObject;

/**
 **/
public class TwoLevelLongPropertyRecord extends TwoLevelPropertyRecord<Subjects, Algorithm, Long> {

    public TwoLevelLongPropertyRecord(long defaultValue) {
        super(() -> new AlgorithmLongPropertyRecord(defaultValue));
    }

    @Override
    PropertyRecord<Algorithm, Long> instantiate(Subjects parent) {
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
