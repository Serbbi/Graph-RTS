package nl.rug.oop.rts.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import nl.rug.oop.rts.model.JSONable;

/**
 * Used to store the data from an object in json format.
 * Used to write to a JSON file.
 * Can be used recursively.
 */
public class JSONObject extends JSON {
    private final Map<String, Object> map;

    /**
     * Constructor initialises the HashMap.
     */
    public JSONObject() {
        this.map = new HashMap<String, Object>();
    }

    /**
     * Puts the key, value pair into the hash map.
     * 
     * @param key   Key to put into the hash map.
     * @param value Value to put into the hash map.
     * @return This json object.
     * @throws NullPointerException Thrown when no key supplied.
     */
    public JSONObject put(String key, Object value) throws NullPointerException {

        if (key == null) {
            throw new NullPointerException("No Key.");
        }

        if (value != null) {
            this.map.put(key, value);
        } else {
            this.remove(key);
        }

        return this;
    }

    /**
     * Puts a key JSONObject pair into the hash map.
     * 
     * @param key   Key to put into the hash map.
     * @param value JSONObject to put into the hash map.
     * @return JSON Object
     * @throws NullPointerException Throws when key is has no value.
     */
    public JSONObject put(String key, JSONObject value) throws NullPointerException {
        put(key, value);

        return this;
    }

    /**
     * Puts a key JSONable pair into the hash map.
     * 
     * @param key   Key to put into the hash map.
     * @param value JSONable to put into the hash map.
     * @return JSON Object
     * @throws NullPointerException Throws when key is has no value.
     */
    public JSONObject put(String key, JSONable value) throws NullPointerException {
        this.put(key, value.toJson());
        return this;
    }

    /**
     * Removes a key from the hashmap.
     * 
     * @param key Key to remove.
     * @return Object
     */
    public Object remove(String key) {
        return this.map.remove(key);
    }

    /**
     * Gets the size of the hash map.
     * 
     * @return int length.
     */
    public int length() {
        return this.map.size();
    }

    /**
     * Writes this JSONObject to a StringWriter.
     * 
     * @param w      StringWriter to write to.
     * @param indent Depth to which the data should indented.
     * @return StringWriter Object.
     * @throws IOException Input output exception.
     */
    protected Object write(StringWriter w, int indent) throws IOException {
        w.write(indent("{", indent));
        Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();

        for (int i = 0; i < length(); i++) {
            Entry<String, ?> entry = iterator.next();
            String key = entry.getKey();
            w.write("\n" + indent(surroundQuotation(key), indent + 1));
            w.write(": ");

            writeValue(w, entry.getValue(), indent);

            w.write(((i < length() - 1) ? "," : "\n"));
        }
        w.write(indent("}", indent));

        return w;
    }
}
