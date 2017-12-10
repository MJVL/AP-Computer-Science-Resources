package org.masonacm.actorwars;

import java.awt.*;

public class IronOre extends Rock {
    public IronOre() {
        this(50);
    }

    IronOre(int hp) {
        super(hp);
        setColor(new Color(100, 30, 0));
    }

    @Override
    void damage(int d, ActiveActor a) {
        super.damage(d, a);
        if(getHealth() <= 0) {
            if(getGrid() != null) removeSelfFromGrid();
            a.addItem(Iron.class);
        }
    }
}
