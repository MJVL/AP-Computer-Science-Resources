package org.masonacm.actorwars;

import info.gridworld.grid.Location;

import java.awt.*;

public class Tree extends Wood {
    public Tree() {
        this(10);
    }

    Tree(int hp) {
        super(hp);
        setColor(new Color(0, (int) (Math.random() * 100) + 100, 0));
    }

    @Override
    public void destructibleAct() {
        super.destructibleAct();
        if(age % 550 == 0) {
            if(getGrid().getEmptyAdjacentLocations(getLocation()).size() > 0) {
                Location l = getGrid().getEmptyAdjacentLocations(getLocation()).get((int) (Math.random() * (getGrid().getEmptyAdjacentLocations(getLocation()).size() - 1)));
                (new Tree()).putSelfInGrid(getGrid(), l);
            }
        }
    }
}
