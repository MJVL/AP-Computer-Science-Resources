package org.masonacm.actorwars;

import java.util.ArrayList;

public class Inventory {
    public static boolean ITEM_ACQUIRE_SOUND = false;

    public ArrayList<Class<?>> mystuff = new ArrayList<Class<?>>();
    public ArrayList<Byte> mycounts = new ArrayList<Byte>();

    public int getItemCount() {
        return mystuff.size();
    }

    public int getItemCount(Class<?> e) {
        if(contains(e)) {
            return mycounts.get(mystuff.indexOf(e));
        }
        return 0;
    }

    public void addItem(Class<?> e) {
        //System.out.println("inventory.addItem(): "+e.getName());
        if(mystuff.contains(e)) {
            mycounts.set(mystuff.indexOf(e), (byte) (mycounts.get(mystuff.indexOf(e)) + 1));
        } else {
            mystuff.add(e);
            mycounts.add((byte) 1);
        }

        if(ITEM_ACQUIRE_SOUND) MP3.playResource("item_acquire.mp3");
    }

    public void removeItem(Class<?> e) {
        if(mystuff.contains(e)) {
            mycounts.set(mystuff.indexOf(e), (byte) (mycounts.get(mystuff.indexOf(e)) - 1));
            if(mycounts.get(mystuff.indexOf(e)) <= 0) {
                mycounts.remove(mystuff.indexOf(e));
                mystuff.remove(mystuff.indexOf(e));
            }
        }
    }

    public boolean contains(Class<?> e) {
        return mystuff.contains(e);
    }
}
