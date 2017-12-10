package org.masonacm.actorwars;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;

public class ActorWarWorld extends ActorWorld {
    public ActorWarWorld() {
        setGrid(new BoundedGrid<Actor>(42, 42));
        int i = 0;
        while(i < 40) {
            add(new Tree());
            i++;
        }
        i = 0;
        while(i < 10) {
            add(new Wheat());
            i++;
        }
        i = 0;
        while(i < 10) {
            add(new Rock());
            i++;
        }
        i = 0;
        while(i < 5) {
            add(new IronOre());
            i++;
        }
        add(new Quarry());
        add(new Quarry());
    }
}
