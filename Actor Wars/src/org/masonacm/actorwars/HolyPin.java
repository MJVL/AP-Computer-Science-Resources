package org.masonacm.actorwars;

public class HolyPin implements Craftable, Useable {
    @Override
    public Inventory getRecipe() {
        Inventory i = new Inventory();
        i.addItem(Iron.class);
        i.addItem(Iron.class);
        i.addItem(Iron.class);
        i.addItem(Rock.class);
        i.addItem(Rock.class);
        i.addItem(Rock.class);
        i.addItem(Wood.class);
        i.addItem(Wood.class);
        i.addItem(Wood.class);
        return i;
    }

    @Override
    public String getName() {
        return "The Holy Pin";
    }

    @Override
    public void use(ActiveActor a) {
        a.removeItem(HolyPin.class);
        a.addItem(HolyHandGrenade.class);
    }
}
