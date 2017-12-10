package org.masonacm.actorwars;


public class Iron extends IronOre implements Resource, Placeable {
    public Iron() {
        this(100);
    }

    Iron(int hp) {
        super(hp);
    }

    @Override
    public String getName() {
        return "Iron";
    }
}
