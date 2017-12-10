package org.masonacm.actorwars;

import info.gridworld.grid.Location;

import java.util.ArrayList;


public abstract class Peon extends ActiveActor {
    public ArrayList<Action> myactions;

    /**
     * This method is equivalent to act(), extend this when making a Peon. It is called once per tick, just like the act() function
     */
    public abstract void peonAct();

    public Peon() {
        super(100);//Peons have 100 energy to start with.
        myactions = new ArrayList<Action>();
    }

    /**
     * Adds Action [a] to bottom of this Peon's Action que
     * @param a The Action to add
     */
    public final void add(Action a) {
        myactions.add(a);
    }

    /**
     * This method is is called once per tick to do the overhead for the Peon class (it is automatic)
     */
    public final void activeAct() {
        peonAct();
        while(myactions.contains(null))
            myactions.remove(null);
        while(myactions.size() > 0) {
            if((!hasActed() && myactions.get(0).isExclusive()) || !myactions.get(0).isExclusive()) {
                //System.out.println("peon.actvact(Action to preform): "+myactions.get(0).getClass().getName());
                perform(myactions.remove(0));
            } else {
                break;
            }
        }
    }

    @Override
    public String toString() {
        String s = this.getClass().getName() + "\n";
        s = s + "INV:" + this.myinv.toString() + "\nACTS:" + this.myactions.toString() + "\nHEALTH:" + getHealth() + "\nENERGY:" + getEnergy();
        return s;
    }


    /**
     * Actor pauses and waits [c] turns before continuing
     * @param c The number of turns to wait
     */
    public static final Action count(final int c) {
        return new Action() {
            int myc = 0;
            boolean set = false;

            @Override
            protected void perform(ActiveActor a) {
                if(!set) {
                    set = true;
                    myc = c;
                }
                myc--;
                System.out.println("Counting:" + (c - myc));
                if(myc > 0)
                    ((Peon) a).myactions.add(0, this);
            }

            @Override
            public int getCost() {
                return 0;
            }

            @Override
            public boolean isExclusive() {
                return true;
            }

            @Override
            public Object getData() {
                return myc;
            }

            @Override
            public String toString() {
                return "Count(" + c + ")";
            }
        };
    }

    /**
     * Assembles a list of movement and turning orders to pathfind to location [l]
     * @param location The location to pathfind to
     */
    public static final Action moveToGradual(final ModifiableLocation location) {
        //	System.out.println("Peon.moveToGradual()");
        return new Action() {
            @Override
            protected void perform(ActiveActor a) {

                if(location == null) return;
             //   System.out.println("Ive got location: "+location);
                ModifiableLocation target = new ModifiableLocation(location.getValue());
                if(a==null) return;
            //    System.out.println("Im not null");
                if(a.getLocation()==null) return;
            //    System.out.println("I know where i am");
                if(a.getGrid()==null) return;
           //     System.out.println("I have a world");
                if(!target.equals(a.getLocation())) {
                    ArrayList<Location> path = Pathfinder.findPath(a.getLocation(), target, a.getGrid());
             //       System.out.println("I'm not there yet");
                if(path == null) return;
             //       System.out.println("Ive got a path: "+path);
                    ((Peon) a).myactions.add(0, Peon.conditionalAct(Utils.notAtLocation(a, new ModifiableLocation(target)), Peon.moveToGradual(new ModifiableLocation(target))));
                    ((Peon) a).myactions.add(0, Peon.conditionalAct(Utils.notAtLocation(a, new ModifiableLocation(target)), Action.move()));
                    ((Peon) a).myactions.add(0, Peon.conditionalAct(Utils.notAtLocation(a, new ModifiableLocation(target)), Action.turn(LocationFinder.directionTo(a, new ModifiableLocation(target)))));
                    while(path.size() > 0) {
                        ((Peon) a).myactions.add(0, Peon.conditionalAct(Utils.notAtLocation(a, new ModifiableLocation(target)), move()));
                        ((Peon) a).myactions.add(0, Peon.conditionalAct(Utils.notAtLocation(a, new ModifiableLocation(target)), turn(LocationFinder.directionTo(a, new ModifiableLocation(path.get(path.size() - 1))))));
                        path.remove(path.size() - 1);
                    }
              //      System.out.println("Im done building actions");
                }
            }

            @Override
            public int getCost() {
                return 0;
            }

            @Override
            public boolean isExclusive() {
                return false;
            }

            @Override
            public Object getData() {
                return location;
            }

            @Override
            public String toString() {
                return "MoveToGradual(" + (location != null ? location.toString() : "null") + ")";
            }
        };
    }

    /**
     * Repeats action [myact] until [b].getValue() returns false
     * @param myact The Action to repeat
     * @param b     The flag for controlling the loop
     */
    public static final Action repeat(final Action myact, final ModifiableBoolean b) {
        return new Action() {
            @Override
            protected void perform(ActiveActor a) {
                if(b.getValue()) {
                    ((Peon) a).myactions.add(0, this);
                    ((Peon) a).myactions.add(0, myact);
                }
            }

            @Override
            public int getCost() {
                return 0;
            }

            @Override
            public boolean isExclusive() {
                return false;
            }

            @Override
            public Object getData() {
                return myact;
            }

            @Override
            public String toString() {
                return "Repeat(" + (myact != null ? myact.toString() : "null") + ", "
                        + (b != null ? b.toString() : "null") + ")";
            }
        };
    }

    /**
     * Performs action [ifaction] if [b] is true
     * @param b        The condition for executing [ifaction]
     * @param ifaction The Action to perform
     */
    public static final Action conditionalAct(final ModifiableBoolean b, final Action ifaction) {
        return new Action() {
            @Override
            protected void perform(ActiveActor a) {
                if(b.getValue()) {
                    ((Peon) a).myactions.add(0, ifaction);
                }

            }

            @Override
            public int getCost() {
                return 0;
            }

            @Override
            public boolean isExclusive() {
                return false;
            }

            @Override
            public Object getData() {
                return new Object[]{b, ifaction};
            }

            @Override
            public String toString() {
                return "ConditionalAct(" + (b != null ? b.toString() : "null") + ", "
                        + (ifaction != null ? ifaction.toString() : "null") + ")";
            }
        };
    }

    /**
     * Preforms action [ifaction] if [b] is true else preforms [elseaction]
     * @param b          The condition for executing [ifaction]
     * @param ifaction   The Action to perform if [b].getValue() returns true
     * @param elseaction The Action to perform if [b].getValue() returns false
     */
    public static Action conditionalAct(final ModifiableBoolean b, final Action ifaction, final Action elseaction) {
        return new Action() {
            @Override
            protected void perform(ActiveActor a) {
                if(b.getValue()) {
                    ((Peon) a).myactions.add(0, ifaction);
                } else {
                    if(elseaction != null) {
                        ((Peon) a).myactions.add(0, elseaction);
                    }
                }

            }

            @Override
            public int getCost() {
                return 0;
            }

            @Override
            public boolean isExclusive() {
                return false;
            }

            @Override
            public Object getData() {
                return new Object[]{b, ifaction, elseaction};
            }

            @Override
            public String toString() {
                return "ConditionalAct(" + (b != null ? b.toString() : "null") + ", "
                        + (ifaction != null ? ifaction.toString() : "null") + ", "
                        + (elseaction != null ? elseaction.toString() : "null") + ")";
            }
        };
    }

    /**
     * Moves to an adjacent location of [l] then places [e] at location [l]
     * @param e The object to place at [l]
     * @param l The location to place [e] at
     */
    public static Action placeAt(final Class<?> e, final Location l) {
        return new Action() {
            @Override
            protected void perform(ActiveActor a) {
                ((Peon) a).myactions.add(0, place(e));
                ((Peon) a).myactions.add(0, turn(LocationFinder.directionTo(a, new ModifiableLocation(l))));
                ((Peon) a).myactions.add(0, Peon.moveToGradual(LocationFinder.findClosestEmptyAdjacentLocation(a, new ModifiableLocation(l))));
            }

            @Override
            public int getCost() {
                return 0;
            }

            @Override
            public boolean isExclusive() {
                return false;
            }

            @Override
            public Object getData() {
                return new Object[]{e, l};
            }

            @Override
            public String toString() {
                return "PlaceAt(" + (e != null ? e.toString() : "null") + ", "
                        + (l != null ? l.toString() : "null") + ")";
            }
        };
    }
}
