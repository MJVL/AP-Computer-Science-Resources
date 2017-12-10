package org.masonacm.actorwars;

import info.gridworld.actor.Actor;

/**
 * General utility methods for ActorWars
 * @author Christopher Krueger
 * @author Schuyler Cebulskie
 */
public final class Utils {
    /**
     * Gets whether or not an Actor is at the ModifiableLocation specified
     * @param actor    The Actor to test
     * @param location The Location to test at
     * @return Whether or not the Actor specified is at the ModifiableLocation
     */
    public static ModifiableBoolean atLocation(final Actor actor, final ModifiableLocation location) {
        return new ModifiableBoolean() {
            @Override
            public boolean getValue() {
                return actor.getLocation().equals(location.getValue());
            }
        };
    }

    /**
     * Gets whether or not an Actor is not at the ModifiableLocation specified
     * @param actor    The Actor to test
     * @param location The Location to test at
     * @return Whether or not the Actor specified is at the ModifiableLocation
     */
    public static ModifiableBoolean notAtLocation(final Actor actor, final ModifiableLocation location) {
        return new ModifiableBoolean() {
            @Override
            public boolean getValue() {
                if(!actor.getLocation().equals(location.getValue())) System.out.println("I'm not there "+location);
                return !actor.getLocation().equals(location.getValue());
            }
        };
    }

    /**
     * Checks to see if an object implements a class
     * @param e The object to check for the implementation
     * @param i The class to look for
     * @return True if the object implements the class, false if it doesn't
     */
    public static boolean isImplemented(Class<?> e, Class<?> i) {
        for(Class<?> c : e.getInterfaces()) {
            if(c.equals(i)) return true;
        }
        return false;
    }
}
