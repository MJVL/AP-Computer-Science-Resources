package org.masonacm.actorwars;


public interface Passable {
    //executed when actor [a] attempts to :move(): onto this actor
    public abstract void stepOn(DestructibleActor a);

    //returns true if an actor can move onto this one going in [direction]
    public abstract boolean canPass(int direction);
}
