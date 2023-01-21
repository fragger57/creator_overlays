package net.fragger.creatoroverlays.client.GUI;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.fragger.creatoroverlays.client.overlays.TPOverlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.fragger.creatoroverlays.event.KeyInputHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.client.TPHandler.*;
import static net.fragger.creatoroverlays.event.KeyInputHandler.tpGUI;


public class TPGuiSection {

    private static final Identifier TOGGLE_ON = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/toggle_on.png");
    private static final Identifier TOGGLE_OFF = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/toggle_off.png");
    private static final Identifier SELECT_ON = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/select_on.png");
    private static final Identifier SELECT_OFF = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/select_off.png");
    private static final Identifier RESET = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/reset_arrow.png");

    private static final Text black = Text.of("Black");
    private static final Text white = Text.of("White");
    private static final Text red = Text.of("Red");
    private static final Text blue = Text.of("Blue");

    private WToggleButton selectTP = new WToggleButton(SELECT_ON, SELECT_OFF);
    private WToggleButton toggleTP = new WToggleButton(TOGGLE_ON, TOGGLE_OFF);
    private TPOverlay overlay;

    int y = 0;
    public TPGuiSection(Text label, int row, TPOverlay tpOverlay, WPlainPanel root) {

        y = row * 20;
        overlay = tpOverlay;

        //section label
        WLabel section = new WLabel(label);
        root.add(section, 10, y - 10);

        //Visibility Toggle
        root.add(toggleTP, 10, y, 20, 20);
        toggleTP.setOnToggle(on -> {
            overlay.updateRenderStatus();
            updatedSelected();
            selectTP.setToggle(true);
            if (overlay.isRendered()) {
                KeyInputHandler.tpOverlay.toggleOn();
            }
        });

        //Change Color
        WButton colorTP = new WButton(getColor());
        root.add(colorTP, 40, y, 70, 20);
        colorTP.setOnClick(() -> {
            overlay.updateColor();
            colorTP.setLabel(getColor());
            updatedSelected();
        });

        //Reset Overlay Position
        WButton reset = new WButton(new TextureIcon(RESET));
        root.add(reset, 120, y, 20, 20);
        reset.setOnClick(() -> {
           overlay.reset();
           updatedSelected();
        });

        //Selection Toggle
        root.add(selectTP, 150, y, 20, 20);
        selectTP.setOnToggle(on -> {
            overlay.updateSelected();
            updatedSelected();
        });
    }
    private Text getColor() {
        if (overlay.color() == 0) {
            return black;
        } else if (overlay.color() == 1) {
            return white;
        } else if (overlay.color() == 2) {
            return red;
        } else {
            return blue;
        }
    }
    private void updatedSelected() {
        if(overlay == tp1) {
            tpGUI.updateSelected(1);
        } else if (overlay == tp2) {
            tpGUI.updateSelected(2);
        } else if (overlay == tp3) {
            tpGUI.updateSelected(3);
        } else if (overlay == tp4) {
            tpGUI.updateSelected(4);
        }
    }
    public void selectOn() {
        selectTP.setToggle(true);
        overlay.setIsSelected(true);
    }
    public void selectOff() {
        selectTP.setToggle(false);
        overlay.setIsSelected(false);
    }
    public void toggleOn() {
        toggleTP.setToggle(true);
        overlay.setIsRendered(true);
        overlay.setRenderStatus(true);
    }
    public void toggleOff() {
        toggleTP.setToggle(false);
        overlay.setIsRendered(false);
    }
}
