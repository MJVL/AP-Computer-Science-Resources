package org.masonacm.actorwars;


public class Pickaxe implements Craftable, Useable {
    private int damg = 5;

    public Pickaxe() {}

    @Override
    public void use(ActiveActor a) {
        if(a.isFacing(Rock.class)) {
            ((DestructibleActor) a.getFacing()).damage(damg, a);
        }

    }

    @Override
    public String getName() {
        return "Pick";
    }

    @Override
    public Inventory getRecipe() {
        Inventory i = new Inventory();
        i.addItem(Wood.class);
        i.addItem(Wood.class);
        i.addItem(Wood.class);
        return i;
    }
}
