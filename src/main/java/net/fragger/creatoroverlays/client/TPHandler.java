package net.fragger.creatoroverlays.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fragger.creatoroverlays.client.overlays.TPOverlay;

import static net.fragger.creatoroverlays.client.GUI.TrackingPointsGUI.*;
import static net.fragger.creatoroverlays.event.KeyInputHandler.tpGUI;

public class TPHandler {

    public static TPOverlay tp1 = new TPOverlay();
    public static TPOverlay tp2 = new TPOverlay();
    public static TPOverlay tp3 = new TPOverlay();
    public static TPOverlay tp4 = new TPOverlay();

    private static boolean isRendered = false;

    public void updateRenderStatus() {
        if (isRendered) {
            row1.toggleOff();
            row2.toggleOff();
            row3.toggleOff();
            row4.toggleOff();
            toggleAll.setToggle(false);
        } else {
            tp1.setIsRendered(tp1.renderStatus());
            tp2.setIsRendered(tp2.renderStatus());
            tp3.setIsRendered(tp3.renderStatus());
            tp4.setIsRendered(tp4.renderStatus());

            if(!tp1.isRendered() & !tp2.isRendered() & !tp3.isRendered() & !tp4.isRendered()) {
                row1.toggleOn();
                row2.toggleOn();
                row3.toggleOn();
                row4.toggleOn();
            }
            if (tp1.isRendered()) {
                row1.toggleOn();
            }
            if (tp2.isRendered()) {
                row2.toggleOn();
            }
            if (tp3.isRendered()) {
                row3.toggleOn();
            }
            if (tp4.isRendered()) {
                row4.toggleOn();
            }
            toggleAll.setToggle(true);
        }
        isRendered = !isRendered;
    }
    public void toggleOn() {
        if(!isRendered) {
            tp1.setIsRendered(tp1.renderStatus());
            tp2.setIsRendered(tp2.renderStatus());
            tp3.setIsRendered(tp3.renderStatus());
            tp4.setIsRendered(tp4.renderStatus());

            if (!tp1.isRendered() & !tp2.isRendered() & !tp3.isRendered() & !tp4.isRendered()) {
                row1.toggleOn();
                row2.toggleOn();
                row3.toggleOn();
                row4.toggleOn();
            }
            if (tp1.isRendered()) {
                row1.toggleOn();
            }
            if (tp2.isRendered()) {
                row2.toggleOn();
            }
            if (tp3.isRendered()) {
                row3.toggleOn();
            }
            if (tp4.isRendered()) {
                row4.toggleOn();
            }
            toggleAll.setToggle(true);
            isRendered = true;
        }
    }
    public void initializePosition() {
        tp1.initializeTPOverlay();
        tp2.initializeTPOverlay();
        tp3.initializeTPOverlay();
        tp4.initializeTPOverlay();
        row1.selectOn();
    }
    public void initializeOverlay() {
        HudRenderCallback.EVENT.register(tp1);
        HudRenderCallback.EVENT.register(tp2);
        HudRenderCallback.EVENT.register(tp3);
        HudRenderCallback.EVENT.register(tp4);
    }
    public void moveUp() {
       tp1.moveUp();
       tp2.moveUp();
       tp3.moveUp();
       tp4.moveUp();
    }
    public void moveUpFast() {
        tp1.moveUpFast();
        tp2.moveUpFast();
        tp3.moveUpFast();
        tp4.moveUpFast();
    }
    public void moveDown() {
        tp1.moveDown();
        tp2.moveDown();
        tp3.moveDown();
        tp4.moveDown();
    }
    public void moveDownFast() {
        tp1.moveDownFast();
        tp2.moveDownFast();
        tp3.moveDownFast();
        tp4.moveDownFast();
    }
    public void moveRight() {
        tp1.moveRight();
        tp2.moveRight();
        tp3.moveRight();
        tp4.moveRight();
    }
    public void moveRightFast() {
        tp1.moveRightFast();
        tp2.moveRightFast();
        tp3.moveRightFast();
        tp4.moveRightFast();
    }
    public void moveLeft() {
        tp1.moveLeft();
        tp2.moveLeft();
        tp3.moveLeft();
        tp4.moveLeft();
    }
    public void moveLeftFast() {
        tp1.moveLeftFast();
        tp2.moveLeftFast();
        tp3.moveLeftFast();
        tp4.moveLeftFast();
    }
    public boolean isRendered() {
        return isRendered;
    }
    public void cycleSelectedUp() {
        if (tp1.isSelected()) {
            tpGUI.updateSelected(row2);
        } else if (tp2.isSelected()) {
            tpGUI.updateSelected(row3);
        } else if (tp3.isSelected()) {
            tpGUI.updateSelected(row4);
        } else if (tp4.isSelected()) {
            tpGUI.updateSelected(row1);
        } else {
            tpGUI.updateSelected(row1);
        }
    }

    public void cycleSelectedDown() {
        if (tp1.isSelected()) {
            tpGUI.updateSelected(row4);
        } else if (tp2.isSelected()) {
            tpGUI.updateSelected(row1);
        } else if (tp3.isSelected()) {
            tpGUI.updateSelected(row2);
        } else if (tp4.isSelected()) {
            tpGUI.updateSelected(row3);
        } else {
            tpGUI.updateSelected(row1);
        }
    }
    public void cycleColor() {
        if (tp1.isSelected()) {
            tp1.updateColor();
            row1.updateColor();
        } else if (tp2.isSelected()) {
            tp2.updateColor();
            row2.updateColor();
        } else if (tp3.isSelected()) {
            tp3.updateColor();
            row3.updateColor();
        } else if (tp4.isSelected()) {
            tp4.updateColor();
            row4.updateColor();
        }
    }
}
