package com.game.main;

import java.awt.Graphics;

/**
 * Created by tluo on 7/7/2017.
 */
public abstract class NoteObject {
    protected int y;
    protected Lane lane;
    protected Note note;
    protected double hitTime;
    protected Handler handler;

    public NoteObject(Lane lane, int y, Note note) {
        this.lane = lane;
        this.y = y;
        this.note = note;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public Lane getLane() {
        return lane;
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public double getHitTime() {
        return hitTime;
    }

    public void setHitTime(double hitTime) {
        this.hitTime = hitTime;
    }

}
