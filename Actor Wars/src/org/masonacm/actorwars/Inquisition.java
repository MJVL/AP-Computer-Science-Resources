package org.masonacm.actorwars;

import info.gridworld.grid.Location;

import java.awt.*;

public class Inquisition extends Peon {
    public Inquisition(Location l) {
        setColor(Color.RED);
        setDirection(90);
        add(Peon.moveToGradual(LocationFinder.findClosestEmptyAdjacentLocation(this, new ModifiableLocation(l))));
        add(Action.halt());
        add(Action.say("Nobody expects the Spanish Inquisition!"));
    }

    @Override
    public void peonAct() {

    }
}
