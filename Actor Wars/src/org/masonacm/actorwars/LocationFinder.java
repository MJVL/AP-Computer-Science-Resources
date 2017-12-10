package org.masonacm.actorwars;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * Provides convenience methods for finding Locations
 * @author Christopher Krueger
 * @author Schuyler Cebulskie
 */
public class LocationFinder {
    /**
     * Finds the Location of the nearest Actor that is an instance of a specified class
     * @param location The Location to find the closest to
     * @param c        The class to check the instance of
     * @param grid     The grid to search on
     * @return The Location of the nearest Actor to the Location
     */
    public static Location findClosestInstance(Location location, Class<?> c, Grid<Actor> grid) {
        ArrayList<Location> locations = new ArrayList<Location>();

        // Find all instances
        for(int row = 0; row < grid.getNumRows(); row++) {
            for(int col = 0; col < grid.getNumCols(); col++) {
                if(c.isInstance(grid.get(new Location(row, col)))) {
                    locations.add(new Location(row, col));
                }
            }
        }

        if(locations.size() == 0) return null;

        // Find the one with the lowest distance
        int min = grid.getNumCols() + grid.getNumRows(), index = 0;
        for(Location l : locations) {
            if(Math.abs(l.getRow() - location.getRow()) + Math.abs(l.getCol() - location.getCol()) < min) {
                index = locations.indexOf(l);
                min = Math.abs(l.getRow() - location.getRow()) + Math.abs(l.getCol() - location.getCol());
            }
        }

        return locations.get(index);
    }

    /**
     * Finds the Location of the nearest Actor that is an instance of a specified class
     * @param actor The Actor to find the closest to
     * @param c     The class to check the instance of
     * @return The Location of the nearest other Actor to the Actor
     */
    public static Location findClosestInstance(Actor actor, Class<?> c) {
        return findClosestInstance(actor.getLocation(), c, actor.getGrid());
    }

    /**
     * Find the closest empty adjacent Location to a Location
     * @param location The Location to find the closest to
     * @return The closest empty adjacent Location to the provided Location
     */
    public static ModifiableLocation findClosestEmptyAdjacentLocation(final Actor a, final ModifiableLocation location) {
        return new ModifiableLocation() {
            @Override
            public Location getValue() {
                if(location == null) return null;

                // Get all of the empty adjacent locations
                ArrayList<Location> arrloc;
                try {
                    arrloc = a.getGrid().getEmptyAdjacentLocations(location.getValue());
                } catch(NullPointerException n) {
                    return null;
                }

                if(arrloc == null || arrloc.size() == 0) return null;

                // Find the closest one
                int min = a.getGrid().getNumCols() + a.getGrid().getNumRows(), index = 0;
                for(Location l : arrloc) {
                    if(Math.abs(l.getRow() - a.getLocation().getRow()) + Math.abs(l.getCol() - a.getLocation().getCol()) < min) {
                        index = arrloc.indexOf(l);
                        min = Math.abs(l.getRow() - a.getLocation().getRow()) + Math.abs(l.getCol() - a.getLocation().getCol());
                    }
                }

                return arrloc.get(index);
            }
        };
    }

    /**
     * Gets the direction from a Location to another Location
     * @param location1 The first Location
     * @param location2 The second Location
     * @return The direction from the Actor to the Location
     */
    public static ModifiableInteger directionTo(final ModifiableLocation location1, final ModifiableLocation location2) {
        return new ModifiableInteger() {
            @Override
            public int getValue() {
                if(location1 == null || location1.getValue() == null || location2 == null || location2.getValue() == null)
                    return 0;
                return location1.getValue().getDirectionToward(location2.getValue());
            }
        };
    }

    /**
     * Gets the direction from an Actor to a Location
     * @param actor    The Actor
     * @param location The Location
     * @return The direction from the Actor to the Location
     */
    public static ModifiableInteger directionTo(final Actor actor, final ModifiableLocation location) {
        return new ModifiableInteger() {
            @Override
            public int getValue() {
                if(actor == null || actor.getLocation() == null) return 0;
                if(location == null || location.getValue() == null) return 0;
                return actor.getLocation().getDirectionToward(location.getValue());
            }
        };
    }

    /**
     * Gets the direction from an Actor to another Actor
     * @param actor1 The first Actor
     * @param actor2 The second Actor
     * @return The direction from the Actor to the Location
     */
    public static ModifiableInteger directionTo(final Actor actor1, final Actor actor2) {
        return new ModifiableInteger() {
            @Override
            public int getValue() {
                if(actor1 == null || actor1.getLocation() == null) return 0;
                if(actor2 == null || actor2.getLocation() == null) return 0;
                return actor1.getLocation().getDirectionToward(actor2.getLocation());
            }
        };
    }
}
