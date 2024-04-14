package net.fragger.creatoroverlays.client;

import net.fragger.creatoroverlays.client.gui.overlayguis.TPGUIRow;
import net.fragger.creatoroverlays.client.overlays.TPOverlay;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.ArrayList;

import static net.fragger.creatoroverlays.client.gui.overlayguis.TPGUI.toggleAll;
import static net.fragger.creatoroverlays.event.KeyInputHandler.tpGUI;

public class TPHandler {

    public static ArrayList<TPGUIRow> tpGUIRows = new ArrayList<TPGUIRow>();
    public static int tpArraySize = 0;
    public static final int MAX_ARRAY_SIZE = 16;

    private static boolean isRendered = false;
    private static boolean multiSelected = false;

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
        if (!tpGUIRows.isEmpty()) {
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
        if (!tpGUIRows.isEmpty()) {
            for (TPGUIRow tpGUIRow : tpGUIRows) {
                tpGUIRow.toggleOff();
            }
        }
        toggleAll.setToggle(false);
        isRendered = false;
    }

    public void moveUp() {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.getY() <= 0 && tp.isSelected() && tp.isRendered()) {
                return;
            }
        }
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            tpGUIRow.getTP().moveUp();
        }
    }
    public void moveUpFast() {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.getY() < 5 && tp.isSelected() && tp.isRendered()) {
                moveUp();
                return;
            }
        }
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.isSelected() && tp.isRendered()) {
                tpGUIRow.getTP().moveUpFast();
            }
        }
    }
    public void moveDown() {
        MinecraftClient client = MinecraftClient.getInstance();
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.getY() >= client.getWindow().getScaledHeight() - tp.getHeight() && tp.isSelected() && tp.isRendered()) {
                return;
            }
        }
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.isSelected() && tp.isRendered()) {
                tpGUIRow.getTP().moveDown();
            }
        }
    }
    public void moveDownFast() {
        MinecraftClient client = MinecraftClient.getInstance();
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.getY() > client.getWindow().getScaledHeight() - tp.getHeight() - 5 && tp.isSelected() && tp.isRendered()) {
                moveDown();
                return;
            }
        }
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.isSelected() && tp.isRendered()) {
                tpGUIRow.getTP().moveDownFast();
            }
        }
    }
    public void moveRight() {
        MinecraftClient client = MinecraftClient.getInstance();
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.getX() >= client.getWindow().getScaledWidth() - tp.getWidth() && tp.isSelected() && tp.isRendered()) {
                return;
            }
        }
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.isSelected() && tp.isRendered()) {
                tpGUIRow.getTP().moveRight();
            }
        }
    }
    public void moveRightFast() {
        MinecraftClient client = MinecraftClient.getInstance();
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.getX() > client.getWindow().getScaledWidth() - tp.getWidth() - 5 && tp.isSelected() && tp.isRendered()) {
                moveRight();
                return;
            }
        }
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.isSelected() && tp.isRendered()) {
                tp.moveRightFast();
            }
        }
    }
    public void moveLeft() {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.getX() <= 0 && tp.isSelected() && tp.isRendered()) {
                return;
            }
        }
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            TPOverlay tp = tpGUIRow.getTP();
            if (tp.isSelected() && tp.isRendered()) {
                tp.moveLeft();
            }
        }
    }
    public void moveLeftFast() {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            if (tpGUIRow.getTP().getX() < 5 && tpGUIRow.getTP().isSelected() && tpGUIRow.getTP().isRendered()) {
                moveLeft();
                return;
            }
        }
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            if (tpGUIRow.getTP().isSelected() && tpGUIRow.getTP().isRendered()) {
                tpGUIRow.getTP().moveLeftFast();
            }
        }
    }

    public boolean isRendered() {
        return isRendered;
    }
    public void cycleSelectedUp() {
        multiSelected = false;
        if (tpArraySize > 1) {
            for (int i = 0; i < tpArraySize; i++) {
                if (tpGUIRows.get(i).getTP().isSelected()) {
                    if (i == tpArraySize - 1) {
                        updateSelected(tpGUIRows.get(0));
                        if (!tpGUIRows.get(0).getTP().isRendered() && isRendered) {
                            cycleSelectedUp();
                        }
                        return;
                    } else {
                        updateSelected(tpGUIRows.get(i + 1));
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
        multiSelected = false;
        if (tpArraySize > 1) {
            for (int i = 0; i < tpArraySize; i++) {
                if (tpGUIRows.get(i).getTP().isSelected()) {
                    if (i == 0) {
                        updateSelected(tpGUIRows.get(tpArraySize - 1));
                        if (!tpGUIRows.get(tpArraySize - 1).getTP().isRendered() && isRendered) {
                            cycleSelectedDown();
                        }
                        return;
                    } else {
                        updateSelected(tpGUIRows.get(i - 1));
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
        multiSelected = false;
    }

    public TPOverlay getSelected() {
        TPOverlay overlay = tpGUIRows.get(0).getTP();
        for (int i = 0; i < tpArraySize; i++) {
            if (tpGUIRows.get(i).getTP().isSelected()) {
                overlay = tpGUIRows.get(i).getTP();
            }
        }
        return overlay;
    }

    public void selectSameColor(int color) {
        int numSelected = 0;
        for (int i = 0; i < tpArraySize; i++) {
            if (tpGUIRows.get(i).getTP().color() == color) {
                tpGUIRows.get(i).select();
                numSelected++;
            }
        }
        multiSelected = numSelected > 1;
    }

    public boolean isMultiSelected() {
        return multiSelected;
    }
}
