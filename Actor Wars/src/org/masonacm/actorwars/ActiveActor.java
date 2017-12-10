package org.masonacm.actorwars;

import info.gridworld.actor.Actor;

import java.util.ArrayList;

/**
 * An Actor that can perform Actions
 * @author Chris Krueger
 */
public abstract class ActiveActor extends DestructibleActor {
    int energy = 200;
    Inventory myinv = new Inventory();
    private boolean acted;

    /**
     * Equivalent to the base act() method on Actors; override this instead of act()
     */
    public abstract void activeAct();

    public ActiveActor() {
        this(15, 200);
    }

    ActiveActor(int ep) {
        this(15, ep);
    }

    ActiveActor(int hp, int ep) {
        super(hp);
        energy = ep;
        myinv = new Inventory();
    }

    final void damage(int d, ActiveActor a) {
        super.damage(d, a);
    }

    /**
     * Tests whether or not the Actor is facing a valid location
     * @return {@code true} if the Location in front of the Actor is valid, {@code false} otherwise
     */
    public final boolean isFacingValidLocation() {
        return getGrid().isValid(getLocation().getAdjacentLocation(getDirection()));
    }

    /**
     * Gets the Actor in the space in front of the Actor, if any
     * @return The Actor in front of the Actor
     */
    public final Actor getFacing() {
        if(isFacingValidLocation()) return getGrid().get(getLocation().getAdjacentLocation(getDirection()));
        return null;
    }

    /**
     * Tests whether or not the Actor is facing an instance of a specific class
     * @param e The class to check for
     * @return {@code true} if the Actor is facing an instance of the class, {@code false} otherwise
     */
    public final boolean isFacing(Class<?> e) {
        if(isFacingValidLocation()) {
            return e.isInstance(getGrid().get(getLocation().getAdjacentLocation(getDirection())));
        }
        return false;
    }

    /**
     * Adds an item to the inventory
     * @param e The class of the item to add
     */
    void addItem(Class<?> e) {
        //System.out.println("active.addItem(adding to inventory): "+ e.getName());
        myinv.addItem(e);
    }

    /**
     * Adds items to the inventory
     * @param e The class of the items
     * @param i The amount to add
     */
    void addItem(Class<?> e, int i) {
        while(i > 0) {
            myinv.addItem(e);
            i--;
        }
    }

    /**
     * Removes an item from the inventory
     * @param e The class of the item to remove
     */
    protected void removeItem(Class<?> e) {
        myinv.removeItem(e);
    }

    /**
     * Removes items from the inventory
     * @param e The class of the items
     * @param i The amount to remove
     */
    protected void removeItem(Class<?> e, int i) {
        while(i > 0) {
            myinv.removeItem(e);
            i--;
        }
    }

    /**
     * Tests if the ActiveActor's inventory contains an item that is an instance of a specific class
     * @param e The class to test for
     * @return {@code true} if the ActiveActor's inventory contains an instance of the class, {@code false} otherwise
     */
    public boolean isHolding(Class<?> e) {
        return myinv.contains(e);
    }

    /**
     * Gets the amount of items of a specific class in the inventory
     * @param e The class to get the amount of
     * @return The number of instances of the class in the inventory
     */
    public int getItemCount(Class<?> e) {
        return myinv.getItemCount(e);
    }

    /**
     * Tests if the ActiveActor can craft an instance of a specific class
     * @param e The class to test if it can be crafted
     * @return {@code true} if the ActiveActor can craft an instance of the class, {@code false} otherwise
     */
    public final boolean canCraft(Class<?> e) {
        Craftable cr = null;
        try {
            cr = (Craftable) e.newInstance();
        } catch(InstantiationException e1) {
            e1.printStackTrace();
        } catch(IllegalAccessException e2) {
            e2.printStackTrace();
        }
        boolean cancraft = true;
        for(Class<?> c : cr.getRecipe().mystuff) {
            if(getItemCount(c) < cr.getRecipe().getItemCount(c)) {
                cancraft = false;
            }
        }
        return cancraft;
    }

    //TODO: JavaDoc here
    public ArrayList<Actor> scan(int range, Class<?> e) {
        ArrayList<Actor> a = new ArrayList<Actor>();
        int x = getLocation().getCol();
        int y = getLocation().getCol();

        return a;
    }

    /**
     * Uses the Useable in front of the Actor, if applicable
     */
    public final void use() {
        if(getGrid().isValid(getLocation().getAdjacentLocation(getDirection()))) {
            Actor b = getFacing();
            if(b instanceof Useable) {
                ((Useable) b).use(this);
            }
        }
    }

    @Override
    public final void destructibleAct() {
        acted = false;
        activeAct();
        if(energy < 0) {
            damage(-(energy / 10), this);
        }
    }

    /**
     * Performs an Action if the ActiveActor has not already performed an exclusive Action this turn
     * @param a The Action to perform
     */
    public final void perform(Action a) {
        if(a == null) return;
        if((a.isExclusive() && !acted) || !a.isExclusive()) {
            a.perform(this);
            if(a.isExclusive()) acted = true;
        }
    }

    /**
     * Gets whether or not the ActiveActor has acted this turn
     * @return {@code true} if the ActiveActor has acted this turn, {@code false} otherwise
     */
    public boolean hasActed() {
        return acted;
    }

    /**
     * Gets the amount of energy the ActiveActor currently has
     * @return The amount of energy the ActiveActor has
     */
    public int getEnergy() {
        return energy;
    }
}
