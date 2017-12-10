package org.masonacm.actorwars;

/**
 * A simple wrapper class for an integer
 * <p>Used so that references to instances are maintained when their value is changed</p>
 * @author Schuyler Cebulskie
 */
public class ModifiableInteger {
    private int value;

    /**
     * Default constructor (value: 0)
     */
    public ModifiableInteger() {
        this.value = 0;
    }

    /**
     * Fill constructor
     * @param value The value to use
     */
    public ModifiableInteger(int value) {
        this.value = value;
    }

    /**
     * Copy constructor
     * @param value The ModifiableInteger to copy from
     */
    public ModifiableInteger(ModifiableInteger value) {
        this.value = value.getValue();
    }

    /**
     * Sets the value of the integer
     * @param value The value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Sets the value of the integer
     * @param value The value to set
     */
    public void setValue(ModifiableInteger value) {
        this.value = value.getValue();
    }

    /**
     * Gets the integer value
     * @return The integer value
     */
    public int getValue() {
        return value;
    }

    /**
     * Increments the integer value
     */
    public void increment() {
        value++;
    }

    /**
     * Decrements the integer value
     */
    public void decrement() {
        value--;
    }

    /**
     * Performs addition on this value
     * @param addend The value to add to this value
     */
    public void add(int addend) {
        this.value += addend;
    }

    /**
     * Performs addition on this value
     * @param addend The value to add to this value
     */
    public void add(ModifiableInteger addend) {
        this.value += addend.getValue();
    }

    /**
     * Performs subtraction on this value
     * @param subtrahend The value to subtract from this value
     */
    public void subtract(int subtrahend) {
        this.value -= subtrahend;
    }

    /**
     * Performs subtraction on this value
     * @param subtrahend The value to subtract from this value
     */
    public void subtract(ModifiableInteger subtrahend) {
        this.value -= subtrahend.getValue();
    }

    /**
     * Performs multiplication on this value
     * @param factor The value to multiply this value by
     */
    public void multiply(int factor) {
        this.value *= factor;
    }

    /**
     * Performs multiplication on this value
     * @param factor The value to multiply this value by
     */
    public void multiply(ModifiableInteger factor) {
        this.value *= factor.getValue();
    }

    /**
     * Performs division on this value
     * @param divisor The value to divide this value by
     */
    public void divide(int divisor) {
        this.value /= divisor;
    }

    /**
     * Performs division on this value
     * @param divisor The value to divide this value by
     */
    public void divide(ModifiableInteger divisor) {
        this.value /= divisor.getValue();
    }

    /**
     * Gets a string representation of the integer
     * @return A string representation of the integer
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }


    /**
     * Performs addition with two ModifiableInteger objects
     * @param addend1 The first value
     * @param addend2 The second value
     * @return The result of the addition
     */
    public static ModifiableInteger add(ModifiableInteger addend1, ModifiableInteger addend2) {
        return new ModifiableInteger(addend1.getValue() + addend2.getValue());
    }

    /**
     * Performs subtraction with two ModifiableInteger objects
     * @param minuend    The value to subtract from
     * @param subtrahend The value to subtract by
     * @return The result of the subtraction
     */
    public static ModifiableInteger subtract(ModifiableInteger minuend, ModifiableInteger subtrahend) {
        return new ModifiableInteger(minuend.getValue() - subtrahend.getValue());
    }

    /**
     * Performs multiplication with two ModifiableInteger objects
     * @param factor1 The value to multiply
     * @param factor2 The value to multiply by
     * @return The result of the multiplication
     */
    public static ModifiableInteger multiply(ModifiableInteger factor1, ModifiableInteger factor2) {
        return new ModifiableInteger(factor1.getValue() * factor2.getValue());
    }

    /**
     * Performs division with two ModifiableInteger objects
     * @param dividend The value to divide
     * @param divisor  The value to divide by
     * @return The result of the division
     */
    public static ModifiableInteger divide(ModifiableInteger dividend, ModifiableInteger divisor) {
        return new ModifiableInteger(dividend.getValue() / divisor.getValue());
    }
}
