package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by tluo on 7/12/2017.
 */
public class KeyInput extends KeyAdapter {

    public static String spaceResult = "";
    public static String fResult = "";
    public static String gResult = "";
    public static String hResult = "";
    public static String jResult = "";

    private static int score = 0;
    public static int combo = 0;

    private Handler handler;
    public static ArrayList hitLanes = new ArrayList(10);
    public static ArrayList hitSetTimes = new ArrayList(10);


    public enum Result {
        MISS(0, 0), BAD(2, 0), GOOD(5, 1), PERFECT(10, 1);
        private int value;
        private int comboAdd;

        Result(int value, int comboAdd) {
            this.value = value;
            this.comboAdd = comboAdd;
        }
    }


    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        Result result;
        Double nextNoteTime;
        switch (key) {
            case KeyEvent.VK_SPACE:
                nextNoteTime = (Double) Tap.spaceNotes.get(0);
                result = hit(nextNoteTime, key);
                if (!result.equals(Result.MISS)) {
                    hitSetTimes.add(Tap.spaceNotes.get(0));
                    Tap.spaceNotes.remove(0);
                    hitLanes.add(Lane.LEFTLEFT);
                }
                break;
            case KeyEvent.VK_F:
                nextNoteTime = (Double) Tap.fNotes.get(0);
                result = hit(nextNoteTime, key);
                if (!result.equals(Result.MISS)) {
                    hitSetTimes.add(Tap.fNotes.get(0));
                    Tap.fNotes.remove(0);
                    hitLanes.add(Lane.LEFT);
                }
                break;
            case KeyEvent.VK_G:
                nextNoteTime = (Double) Tap.gNotes.get(0);
                result = hit(nextNoteTime, key);
                if (!result.equals(Result.MISS)) {
                    hitSetTimes.add(Tap.gNotes.get(0));
                    Tap.gNotes.remove(0);
                    hitLanes.add(Lane.LEFTCENTER);
                }
                break;
            case KeyEvent.VK_H:
                nextNoteTime = (Double) Tap.hNotes.get(0);
                result = hit(nextNoteTime, key);
                if (!result.equals(Result.MISS)) {
                    hitSetTimes.add(Tap.hNotes.get(0));
                    Tap.hNotes.remove(0);
                    hitLanes.add(Lane.RIGHTCENTER);
                }
                break;
            case KeyEvent.VK_J:
                nextNoteTime = (Double) Tap.jNotes.get(0);
                result = hit(nextNoteTime, key);
                if (!result.equals(Result.MISS)) {
                    hitSetTimes.add(Tap.jNotes.get(0));
                    Tap.jNotes.remove(0);
                    hitLanes.add(Lane.RIGHT);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }

    private Result hit(double nextNoteTime, int key) {
        double distance = Math.abs(nextNoteTime - Conductor.songPosition);
        Result tempResult;
        if (distance < 0.175) {
            tempResult = Result.BAD;
            if (distance < 0.0825) {
                tempResult = Result.GOOD;
                if (distance < 0.05)
                    tempResult = Result.PERFECT;
            }
            display(tempResult, key);
        } else {
            tempResult = Result.MISS;
        }
        combo += tempResult.comboAdd;
        score = score + (int) (tempResult.value * 10 * ((double) (100 + combo)) / 100);
        return tempResult;
    }

    private void display(Result result, int key) {
        switch (key) {
            case KeyEvent.VK_SPACE:
                spaceResult = result.toString().toLowerCase();
                break;
            case KeyEvent.VK_F:
                fResult = result.toString().toLowerCase();
                break;
            case KeyEvent.VK_G:
                gResult = result.toString().toLowerCase();
                break;
            case KeyEvent.VK_H:
                hResult = result.toString().toLowerCase();
                break;
            case KeyEvent.VK_J:
                jResult = result.toString().toLowerCase();
                break;
        }
    }

    public int getScore() {
        return score;
    }


}
