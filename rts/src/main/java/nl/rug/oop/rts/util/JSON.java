package nl.rug.oop.rts.util;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Parent class for JSONObject and JSONArray.
 * Holds common methods for both.
 */
public class JSON {
    /**
     * Produces a string in JSON format.
     * 
     * @param indent Depth to indent the data.
     * @return JSON String representation.
     * @throws IOException Input output exception.
     */
    public String toString(int indent) throws IOException {
        StringWriter w = new StringWriter();
        synchronized (w.getBuffer()) {
            return this.write(w, indent).toString();
        }
    }

    protected Object write(StringWriter w, int indent) throws IOException {
        return w;
    }

    /**
     * Indents the value to the specified depth.
     * 
     * @param val    Value to indent.
     * @param indent Depth to which it should indented.
     * @return Indented String
     */
    protected String indent(String val, int indent) {
        String indentation = "";
        for (int i = 0; i < indent; i++) {
            indentation += "  ";
        }
        return indentation + val;
    }

    /**
     * Writes a value from the hash table to the StringWriter.
     * 
     * @param w      StringWriter to write value to.
     * @param value  Value to write.
     * @param indent Depth to indent the value to.
     * @throws IOException Input output exception.
     */
    public void writeValue(StringWriter w, Object value, int indent) throws IOException {
        if (value == null || value.equals(null)) {
            w.write("null");
        } else if (value instanceof JSON) {
            // Works for both a JSONObject and a JSONArray.
            w.write(((JSON) value).toString(indent + 1));
        } else if (value instanceof String) {
            w.write(surroundQuotation((String) value));
        } else if (value instanceof Integer) {
            w.write(Integer.toString((int) value));
        }
    }

    /**
     * Surrounds a supplied value with quotation marks. Used for writing strings and
     * keys to JSON.
     * 
     * @param value Value to surround with quotation marks.
     * @return String "value"
     */
    public String surroundQuotation(String value) {
        return '"' + value + '"';
    }

}
