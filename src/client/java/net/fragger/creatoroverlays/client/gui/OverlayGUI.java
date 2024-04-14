package net.fragger.creatoroverlays.client.gui;

import net.fragger.creatoroverlays.client.StaticOverlay;

public interface OverlayGUI {

    public void updateSprite();
    public void toggle();
    public void toggle(boolean display);
    public void changeColor();
    public StaticOverlay getOverlay();
}
