package org.masonacm.actorwars;

import info.gridworld.actor.Actor;


public class HolyHandGrenade implements Resource, Useable {
    @Override
    public String getName() {
        return "The Holy Hand Grenade of Antioch";
    }

    @Override
    public void use(ActiveActor a) {
        if(a.isFacingValidLocation()) {
            if(!a.isFacing(Actor.class)) {
                HolyHandGrenadeBearer h = new HolyHandGrenadeBearer();
                h.putSelfInGrid(a.getGrid(), a.getLocation().getAdjacentLocation(a.getDirection()));
            }
        }
    }

    public static void give(ActiveActor a) {
        a.addItem(HolyPin.class);
    }
}
