package fsynth.program.db.record;

import fsynth.program.subject.Subjects;
import fsynth.program.Algorithm;
import org.json.JSONObject;

/**
 * @author Lukas Kirschner
 * @since 2020-05-07
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
