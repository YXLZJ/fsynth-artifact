package fsynth.program.db.record;

import fsynth.program.db.JSONSerializable;
import org.json.JSONObject;

/**
 * @author Lukas Kirschner
 * @since 2020-12-21
 **/
public class JsonBoolean implements JSONSerializable {
    private boolean value;

    public JsonBoolean(boolean initialValue) {
        this.value = initialValue;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject dummyObj = new JSONObject();
        dummyObj.put("boolValue", this.value);
        return dummyObj;
    }

    @Override
    public void fromJSON(JSONObject jsonObject) {
        this.value = jsonObject.getBoolean("boolValue");
    }

    /**
     * Get the value stored in this boolean
     *
     * @return the old value
     */
    public boolean get() {
        return this.value;
    }

    /**
     * Set the new value to be stored in this boolean
     *
     * @param newValue New value to set
     */
    public void set(boolean newValue) {
        this.value = newValue;
    }
}
