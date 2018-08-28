package com.twopeople.game;

import java.awt.*;


public class UIElement {
    protected int x, y;
    protected float opacity = 1f;
    protected boolean remove = false;

    public UIElement() {
    }

    public void update() {
    }

    public void render(Graphics g) {
    }

    public boolean shouldRemove() {
        return this.remove;
    }
}