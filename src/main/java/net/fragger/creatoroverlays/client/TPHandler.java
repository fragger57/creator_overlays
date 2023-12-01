package net.fragger.creatoroverlays.client;

import net.fragger.creatoroverlays.client.gui.overlayguis.TPGUIRow;
import net.minecraft.text.Text;

import java.util.ArrayList;

import static net.fragger.creatoroverlays.client.gui.RootGUI.tpGUI;
import static net.fragger.creatoroverlays.client.gui.overlayguis.TPGUI.toggleAll;

public class TPHandler {

    public static ArrayList<TPGUIRow> tpGUIRows = new ArrayList<TPGUIRow>();
    public static int tpArraySize = 0;
    public static final int MAX_ARRAY_SIZE = 16;

    private static boolean isRendered = false;

    public void addTP() {
        if (tpGUIRows.size() < MAX_ARRAY_SIZE) {
            tpGUI.addRow(tpArraySize);
            tpGUIRows.get(tpArraySize).initilize();
            tpArraySize++;
            toggleAllOn();
        }
    }

    public void updateRenderStatus() {
        if (isRendered) {
            toggleAllOff();
        } else {
            toggleAllOn();
        }
    }

    public void updateArray() {
        tpArraySize = 0;
        ArrayList<TPGUIRow> tempArray = new ArrayList<TPGUIRow>();
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            if (tpGUIRow != null) {
                tpGUIRow.updateLabel(Text.of("Tracking Point " + (tpArraySize + 1)));
                tempArray.add(tpGUIRow);
                tpArraySize++;
            }
        }
        tpGUIRows = tempArray;
    }

    public void toggleAllOn() {
        if (tpGUIRows.size() != 0) {
            int renderTotal = 0;
            for (int i = 0; i < tpArraySize; i++) {
                if (!tpGUIRows.get(i).getTP().invisible()) {
                    tpGUIRows.get(i).toggleOn();
                    renderTotal++;
                }
            }
            if (renderTotal == 0) {
                for (int i = 0; i < tpArraySize; i++) {
                    tpGUIRows.get(i).toggleOn();
                    tpGUIRows.get(i).getTP().setInvisible(false);
                }
            }
            toggleAll.setToggle(true);
            isRendered = true;
        }
    }

    public void toggleAllOff() {
        if (tpGUIRows.size() != 0) {
            for (TPGUIRow tpGUIRow : tpGUIRows) {
                tpGUIRow.toggleOff();
            }
        }
        toggleAll.setToggle(false);
        isRendered = false;
    }

    public void moveUp() {
       for (TPGUIRow tpGUIRow : tpGUIRows) {
           tpGUIRow.getTP().moveUp();
       }
    }
    public void moveUpFast() {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            tpGUIRow.getTP().moveUpFast();
        }
    }
    public void moveDown() {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            tpGUIRow.getTP().moveDown();
        }
    }
    public void moveDownFast() {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            tpGUIRow.getTP().moveDownFast();
        }
    }
    public void moveRight() {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            tpGUIRow.getTP().moveRight();
        }
    }
    public void moveRightFast() {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            tpGUIRow.getTP().moveRightFast();
        }
    }
    public void moveLeft() {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            tpGUIRow.getTP().moveLeft();
        }
    }
    public void moveLeftFast() {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            tpGUIRow.getTP().moveLeftFast();
        }
    }

    public boolean isRendered() {
        return isRendered;
    }
    public void cycleSelectedUp() {
        if (tpArraySize > 1) {
            for (int i = 0; i < tpArraySize; i++) {
                if (tpGUIRows.get(i).getTP().isSelected()) {
                    if (i == tpArraySize - 1) {
                        tpGUIRows.get(i).deselect();
                        tpGUIRows.get(0).select();
                        if (!tpGUIRows.get(0).getTP().isRendered() && isRendered) {
                            cycleSelectedUp();
                        }
                        return;
                    } else {
                        tpGUIRows.get(i).deselect();
                        tpGUIRows.get(i + 1).select();
                        if (!tpGUIRows.get(i + 1).getTP().isRendered() && isRendered) {
                            cycleSelectedUp();
                        }
                        return;
                    }
                }
            }
        }
    }

    public void cycleSelectedDown() {
        if (tpArraySize > 1) {
            for (int i = 0; i < tpArraySize; i++) {
                if (tpGUIRows.get(i).getTP().isSelected()) {
                    if (i == 0) {
                        tpGUIRows.get(i).getTP().updateSelected();
                        tpGUIRows.get(tpArraySize - 1).getTP().updateSelected();
                        if (!tpGUIRows.get(tpArraySize - 1).getTP().isRendered() && isRendered) {
                            cycleSelectedDown();
                        }
                        return;
                    } else {
                        tpGUIRows.get(i).getTP().updateSelected();
                        tpGUIRows.get(i - 1).getTP().updateSelected();
                        if (!tpGUIRows.get(i - 1).getTP().isRendered() && isRendered) {
                            cycleSelectedDown();
                        }
                        return;
                    }
                }
            }
        }
    }
    public void cycleColor() {
        for (int i = 0; i < tpArraySize; i++) {
            if (tpGUIRows.get(i).getTP().isSelected()) {
                tpGUIRows.get(i).updateColor();
                return;
            }
        }
    }

    public void updateSelected(TPGUIRow row) {
        for (int i = 0; i < tpArraySize; i++) {
            if (tpGUIRows.get(i) == row) {
                tpGUIRows.get(i).select();
            } else {
                tpGUIRows.get(i).deselect();
            }
        }
    }
}
