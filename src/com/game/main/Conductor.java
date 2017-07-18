package com.game.main;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.*;

/**
 * Created by tluo on 7/10/2017.
 */
public class Conductor {
    private double bpm;
    private double crotchet;
    private double offset;
    public static double songPosition;
    public static double songDuration;
    private String soundFile;
    private MediaPlayer mediaPlayer;
    public int[] lane;
    public double[] time;
    public boolean[] made;
    private Handler handler;
    private Lane noteLane;
    private double lastReset;


    public Conductor(Handler handler) {
        this.handler = handler;
        getNotes();
        this.soundFile = "src/Fire Aura.mp3";
        Media hit = new Media(new File(soundFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setStartTime(Duration.millis(0));
        this.mediaPlayer = mediaPlayer;
        songDuration = this.mediaPlayer.getTotalDuration().toSeconds();


        lastReset = System.currentTimeMillis();
        mediaPlayer.play();

    }
    // test
    //test

    public void tick() {
        if (System.currentTimeMillis() - lastReset > 500) {
            KeyInput.spaceResult = "";
            KeyInput.fResult = "";
            KeyInput.gResult = "";
            KeyInput.hResult = "";
            KeyInput.jResult = "";
            lastReset = System.currentTimeMillis();
        }
        songDuration = this.mediaPlayer.getCycleDuration().toSeconds();

        songPosition = this.mediaPlayer.getCurrentTime().toSeconds();

        for (int i = 0; i < lane.length; i++) {
            if (songPosition + 1.0 > time[i] && made[i] == false) {
                switch (lane[i]) {
                    case 0:
                        noteLane = Lane.LEFTLEFT;
                        break;
                    case 1:
                        noteLane = Lane.LEFT;
                        break;
                    case 2:
                        noteLane = Lane.LEFTCENTER;
                        break;
                    case 3:
                        noteLane = Lane.RIGHTCENTER;
                        break;
                    case 4:
                        noteLane = Lane.RIGHT;
                        break;
                    default:
                        noteLane = Lane.LEFTLEFT;
                }
                handler.addNote(new Tap(noteLane, 0, Note.TAP, time[i], handler));
                made[i] = true;
            }
        }
    }


    public void getNotes() { //STILL NEED TO FIX THIS...
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("src/FireAuraNotes.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int lines = 0;
        try {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lane = new int[lines];
        time = new double[lines];
        made = new boolean[lines];
        String line;

        try {
            reader = new BufferedReader(new FileReader("src/FireAuraNotes.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < lines; i++) {
                line = reader.readLine();
                if (!line.equals("-")) {
                    String[] splitStr = line.split("\\s+");
                    lane[i] = Integer.parseInt(splitStr[0]);
                    time[i] = Double.parseDouble(splitStr[1]);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*for (int i = 0; i < lines; i++) {
            System.out.println(lane[i] + " " + time[i]);
        }*/

    }

    public double getSongDuration() {
        return songDuration;
    }


}
