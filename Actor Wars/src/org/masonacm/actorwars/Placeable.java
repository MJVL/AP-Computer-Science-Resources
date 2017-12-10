package org.masonacm.actorwars;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public interface Placeable {
    public abstract void place(Grid<Actor> g, Location l);
}
