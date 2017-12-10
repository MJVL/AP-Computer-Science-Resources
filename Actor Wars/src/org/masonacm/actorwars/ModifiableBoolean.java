package org.masonacm.actorwars;

/**
 * A simple wrapper class for a boolean
 * <p>Used so that references to instances are maintained when their value is changed</p>
 * @author Schuyler Cebulskie
 */
public class ModifiableBoolean {
    private boolean value;

    /**
     * Default constructor (value: false)
     */
    public ModifiableBoolean() {
        this.value = false;
    }

    /**
     * Fill constructor
     * @param value The value to use
     */
    public ModifiableBoolean(boolean value) {
        this.value = value;
    }

    /**
     * Copy constructor
     * @param value The ModifiableBoolean to copy from
     */
    public ModifiableBoolean(ModifiableBoolean value) {
        this.value = value.getValue();
    }

    /**
     * Sets the value of the boolean
     * @param value The value to set
     */
    public void setValue(boolean value) {
        this.value = value;
    }

    /**
     * Sets the value of the boolean
     * @param value The value to set
     */
    public void setValue(ModifiableBoolean value) {
        this.value = value.getValue();
    }

    /**
     * Gets the boolean value
     * @return The boolean value
     */
    public boolean getValue() {
        return value;
    }

    /**
     * Inverts the boolean value (true becomes false and false becomes true)
     */
    public void invert() {
        value = !value;
    }

    /**
     * Gets a string representation of the boolean ("true" or "false")
     * @return A string representation of the boolean
     */
    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
