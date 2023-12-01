package net.fragger.creatoroverlays.client.gui;

import net.fragger.creatoroverlays.client.StaticOverlay;

public interface OverlayGUI {

    public void updateSprite();
    public void toggle();
    public void changeColor();
    public StaticOverlay getOverlay();
}
