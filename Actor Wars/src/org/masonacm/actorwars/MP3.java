package org.masonacm.actorwars;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * MP3 sound player
 * @author Schuyler Cebulskie
 */
public class MP3 {
    /**
     * Plays an MP3 file from an InputStream
     * @param is The InputStream
     */
    public static void play(final InputStream is) {
        try {
            final BufferedInputStream bis = new BufferedInputStream(is);
            final Player player = new Player(bis);

            new Thread() {
                public void run() {
                    try {
                        player.play();
                    } catch(Exception e) {
                        System.out.println(e);
                    }
                }
            }.start();
        } catch(Exception e) {
            System.out.println("Problem playing stream " + is.toString());
            System.out.println(e);
        }
    }

    /**
     * Plays an MP3 file
     * @param file The filename of the file
     */
    public static void playFile(String file) {
        try {
            FileInputStream is = new FileInputStream(file);
            play(is);
        } catch(Exception e) {
            System.out.println("Problem playing file " + file);
            System.out.println(e);
        }
    }

    /**
     * Plays an MP3 file from the resources
     * @param resource The filename of the resource
     */
    public static void playResource(String resource) {
        try {
            InputStream is = MP3.class.getResourceAsStream(resource);
            play(is);
        } catch(Exception e) {
            System.out.println("Problem playing resource " + resource);
            System.out.println(e);
        }
    }
}
