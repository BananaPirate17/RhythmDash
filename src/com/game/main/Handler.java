package com.game.main;

import java.util.LinkedList;
import java.awt.Graphics;
/**
 * Created by tluo on 7/7/2017.
 */
public class Handler {

    LinkedList<NoteObject> object = new LinkedList<NoteObject>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            NoteObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            NoteObject tempObject = object.get(i);

            tempObject.render(g);
        }

    }

    public void addNote(NoteObject object) {
        this.object.add(object);
    }

    public void removeNote(NoteObject object) {
        this.object.remove(object);
    }
}
