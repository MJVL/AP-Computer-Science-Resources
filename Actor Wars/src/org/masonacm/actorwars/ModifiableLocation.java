package org.masonacm.actorwars;

import info.gridworld.grid.Location;

/**
 * A simple wrapper class for an integer
 * <p>Used so that references to instances are maintained when their value is changed</p>
 * @author Schuyler Cebulskie
 */
public class ModifiableLocation {
    private Location value;

    /**
     * Default constructor (value: (0, 0))
     */
    public ModifiableLocation() {
        this.value = new Location(0, 0);
    }

    /**
     * Fill constructor (maintains reference to the Location)
     * @param value The Location to use
     */
    public ModifiableLocation(Location value) {
        this.value = value;
    }

    /**
     * Fill constructor
     * @param row The row of the Location to use
     * @param col The column of the Location to use
     */
    public ModifiableLocation(int row, int col) {
        this.value = new Location(row, col);
    }

    /**
     * Copy constructor (does not maintain reference to the Location)
     * @param value The Location to use
     */
    public ModifiableLocation(ModifiableLocation value) {
        this.value = new Location(value.getRow(), value.getCol());
    }

    /**
     * Sets the Location value (maintains reference to the Location)
     * @param value The Location to use
     */
    public void setValue(Location value) {
        this.value = value;
    }

    /**
     * Sets the Location value (does not maintain reference to the Location)
     * @param value The Location to use
     */
    public void setValue(ModifiableLocation value) {
        this.value = new Location(value.getRow(), value.getCol());
    }

    /**
     * Sets the Location value
     * @param row The row of the Location to use
     * @param col The column of the Location to use
     */
    public void setValue(int row, int col) {
        this.value = new Location(row, col);
    }

    /**
     * Gets the Location value
     * @return The Location value
     */
    public Location getValue() {
        return value;
    }

    /**
     * Gets the row of the Location
     * @return The row of the Location
     */
    public int getRow() {
        return value.getRow();
    }

    /**
     * Gets the column of the Location
     * @return The column of the Location
     */
    public int getCol() {
        return value.getCol();
    }

    /**
     * Gets a string representation of the Location ((row, col))
     * @return A string representation of the Location
     */
    @Override
    public String toString() {
        return value.toString();
    }
}
