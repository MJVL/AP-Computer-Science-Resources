package org.masonacm.actorwars;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * Resource (4 HP)
 * <p>Use in inventory to regain energy</p>
 */
public class Wheat extends AgingDestructibleActor implements Resource, Useable, Placeable {
    public Wheat() {
        this(4);
    }

    Wheat(int hp) {
        super(hp);
        setColor(new Color(109, 76, 0));
    }

    @Override
    public void destructibleAct() {
        super.destructibleAct();
        if(age % 200 == 0) {
            if(getGrid().getEmptyAdjacentLocations(getLocation()).size() > 0) {
                Location l = getGrid().getEmptyAdjacentLocations(getLocation()).get((int) (Math.random() * (getGrid().getEmptyAdjacentLocations(getLocation()).size() - 1)));
                (new Wheat()).putSelfInGrid(getGrid(), l);
            }
        }
    }

    @Override
    void damage(int d, ActiveActor a) {
        super.damage(d, a);
        if(getHealth() <= 0) {
            if(getGrid() != null) removeSelfFromGrid();
            if(age >= 10) a.addItem(Wheat.class);
            if(age >= 20) a.addItem(Wheat.class);
        }
    }

    @Override
    public String getName() {
        return "Wheat";
    }

    @Override
    public void use(ActiveActor a) {
        if(getGrid() == null) {
            a.energy += 300;
            a.removeItem(this.getClass());
        }
    }

    @Override
    public void place(Grid<Actor> g, Location l) {
        putSelfInGrid(g, l);
    }
}
