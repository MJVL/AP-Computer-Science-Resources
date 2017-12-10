package org.masonacm.actorwars;

import info.gridworld.actor.Actor;

import java.awt.*;

public class HolyHandGrenadeBearer extends Peon {
    boolean charging = true;

    public HolyHandGrenadeBearer() {
        setColor(Color.BLACK);
    }

    @Override
    public void peonAct() {
        if(charging && isFacingValidLocation()) {
            if(!isFacing(Actor.class)) add(Action.move());
            else charging = false;
        }

        if(isFacing(DestructibleActor.class)) {
            getFacing().removeSelfFromGrid();
            removeSelfFromGrid();
            System.out.println("Boom!");
        } else if(!isFacingValidLocation()) {
            myactions.clear();
        } else if(myactions.size() == 0) {
            setDirection(getDirection() + 45);
        }
    }
}
