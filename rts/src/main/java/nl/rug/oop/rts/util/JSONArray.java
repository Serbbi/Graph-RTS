package nl.rug.oop.rts.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to store and write a list of Objects in JSON format.
 */
public class JSONArray extends JSON {
    private List<Object> list;

    /**
     * Constructor initialises the list.
     */
    public JSONArray() {
        list = new ArrayList<>();
    }

    /**
     * Adds an object to the list.
     * 
     * @param obj Object to be added to the list
     */
    public void put(Object obj) {
        list.add(obj);
    }

    /**
     * gets the length of the list.
     * 
     * @return Length of list.
     */
    public int length() {
        return list.size();
    }

    /**
     * Writes the list to a list in JSON format.
     * 
     * @param w      StringWrite to write list to.
     * @param indent Depth to indent the list.
     * @return StringWrite object.
     * @throws IOException Input output exception.
     */
    protected Object write(StringWriter w, int indent) throws IOException {
        w.write("[" + (length() > 0 ? "\n" : ""));

        for (int i = 0; i < length(); i++) {
            Object obj = list.get(i);

            writeValue(w, obj, indent);

            w.write((((i < length() - 1) ? "," : "") + "\n"));
        }
        if (length() > 0) {
            w.write(indent("]", indent));
        } else {
            w.write("]");
        }

        return w;
    }

}
