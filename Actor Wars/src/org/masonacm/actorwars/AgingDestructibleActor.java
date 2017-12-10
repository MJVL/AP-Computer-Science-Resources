package org.masonacm.actorwars;

public abstract class AgingDestructibleActor extends DestructibleActor {
    int age = 0;

    public AgingDestructibleActor() {
        super();
    }

    AgingDestructibleActor(int hp) {
        super(hp);
    }

    /**
     * Gets the age of the AgingDestructibleActor
     * @return The age of the AgingDestructibleActor
     */
    public int getAge() {
        return age;
    }

    /**
     * Increments the age of the AgingDestructibleActor
     */
    void age() {
        if(age < Integer.MAX_VALUE) age++;
        else age = 0;
    }

    @Override
    public void destructibleAct() {
        age();
    }
}
