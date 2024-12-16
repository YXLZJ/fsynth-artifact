package fsynth.program.db.record;

import fsynth.program.subject.Subjects;
import fsynth.program.Algorithm;
import org.json.JSONObject;

/**
 * @author Lukas Kirschner
 * @since 2020-05-07
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
