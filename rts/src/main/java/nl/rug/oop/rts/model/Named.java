package nl.rug.oop.rts.model;

/**
 * Used on all items that have a name to allow the to be shown in the left bar.
 */
public interface Named {

    /**
     * Getter for the name field.
     * 
     * @return Name of the named object.
     */
    String getName();

    /**
     * Setter for the name field.
     * 
     * @param name Name of the named object.
     */
    void setName(String name);
}
