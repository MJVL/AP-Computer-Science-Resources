package org.masonacm.actorwars;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public abstract class DestructibleActor extends Actor {
    int health = 15;

    //abstract funtion for extensions, equivalent to :act():
    public abstract void destructibleAct();

    public DestructibleActor() {
        this(15);
    }

    DestructibleActor(int hp) {
        health = hp;
    }

    //returns true if actor is getFacing an empty location, or a {passable} actor that allows passage
    public boolean canMove() {
        if(getGrid().isValid(getLocation().getAdjacentLocation(getDirection()))) {
            if(getGrid().get(getLocation().getAdjacentLocation(getDirection())) == null) {
                //System.out.println("destructable.canmove(empty space found)");
                return true;
            }
            if(getGrid().get(getLocation().getAdjacentLocation(getDirection())) instanceof Passable) {
                //System.out.println("destructable.canmove(passable detected)");
                return ((Passable) getGrid().get(getLocation().getAdjacentLocation(getDirection()))).canPass(getDirection());
            }
        }
        //System.out.println("destructable.canmove(failed)");
        return false;
    }

    //moves actor forward if :canmove(): returns true
    public void move() {
        if(canMove()) {
            if(getGrid().get(getLocation().getAdjacentLocation(getDirection())) instanceof Passable) {
                ((Passable) getGrid().get(getLocation().getAdjacentLocation(getDirection()))).stepOn(this);
            } else {
                Grid<Actor> gr = getGrid();
                Location l = getLocation().getAdjacentLocation(getDirection());
                if(getGrid() != null)
                    removeSelfFromGrid();
                //System.out.println("destructable.move(Removed from grid)");
                if(getGrid() == null)
                    putSelfInGrid(gr, l);
                //System.out.println("destructable.move(Replaced on grid)");
            }
        }
    }

    //decrements :health: by [d] damage, removes actor if :health:<0
    void damage(int d, ActiveActor a) {
        health = health - d;
        //System.out.println("destructable.damage("+health+"):remaining");
        if(health < 0) {
            if(getGrid() != null)
                removeSelfFromGrid();
        }
    }

    //executes :destact(): and removes actor if :health: == 0
    final public void act() {
        destructibleAct();

        if(health <= 0) {
            if(getGrid() != null)
                removeSelfFromGrid();
        }
    }

    public final int getHealth() {
        return health;
    }
}
