package org.masonacm.actorwars;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * Resource (15 HP)
 * <p>For crafting</p>
 */
public class Wood extends AgingDestructibleActor implements Resource, Placeable {
    public Wood() {
        this(15);
    }

    public Wood(int hp) {
        super(hp);
        setColor(new Color(109, 36, 0));
    }

    @Override
    public void destructibleAct() {
        super.destructibleAct();
    }

    @Override
    void damage(int d, ActiveActor a) {
        super.damage(d, a);
        if(getHealth() <= 0) {
            if(getGrid() != null) removeSelfFromGrid();
            a.addItem(Wood.class);
        }
    }

    @Override
    public String getName() {
        return "Wood";
    }

    @Override
    public void place(Grid<Actor> g, Location l) {
        if(getGrid() == null) putSelfInGrid(g, l);
    }
}
