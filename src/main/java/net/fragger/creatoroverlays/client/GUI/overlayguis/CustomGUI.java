package net.fragger.creatoroverlays.client.gui.overlayguis;

import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.fragger.creatoroverlays.client.gui.AbstractGUI;
import net.fragger.creatoroverlays.client.overlays.CustomOverlay;
import net.minecraft.text.Text;

import static net.fragger.creatoroverlays.client.gui.RootGUI.*;
import static net.fragger.creatoroverlays.event.KeyInputHandler.coScreen;

public class CustomGUI extends AbstractGUI {

    public WPlainPanel customPanel = new WPlainPanel();

    private WToggleButton toggle;
    private WButton cycleUp;
    private WButton cycleDown;
    private WSprite texture;

    private static CustomOverlay overlay = new CustomOverlay();

    public CustomGUI() {

        customPanel.setSize(guiWidth, guiHeight);

        WLabel head = new WLabel(Text.translatable("key.creatoroverlays.gui.custom"));
        head.setHorizontalAlignment(HorizontalAlignment.CENTER);
        customPanel.add(head, 120, 10);

        toggle = new WToggleButton(TOGGLE_ON, TOGGLE_OFF);
        customPanel.add(toggle, 10, y);
        toggle.setOnToggle(on -> {
            toggle();
        });

        cycleDown = new WButton(Text.of("<"));
        customPanel.add(cycleDown, 190, y, 20, 20);
        cycleDown.setOnClick(() -> {
            changeOverlayDown();
        });

        cycleUp = new WButton(Text.of(">"));
        customPanel.add(cycleUp, 220, y, 20, 20);
        cycleUp.setOnClick(() -> {
            changeOverlayUp();
        });



        WSprite outline = new WSprite(OUTLINE);
        customPanel.add(outline, 10, y * 2, 230, 131);

        updateSprite();

        WButton close = new WButton(Text.translatable("key.creatoroverlays.gui.close"));
        customPanel.add(close, 200, 200, 40, 20);
        close.setOnClick(() -> {
            coScreen.close();
        });
    }

    public void updateSprite() {
        customPanel.remove(texture);
        texture = new WSprite(overlay.getTexture());
        customPanel.add(texture, 12, y * 2 + 2, 226, 127);
    }

    public void toggle() {
        overlay.updateRenderStatus();
        toggle.setToggle(overlay.isRendered());
    }

    public void changeOverlayUp() {
        overlay.rotateUp();
        updateSprite();
    }

    public void changeOverlayDown() {
        overlay.rotateDown();
        updateSprite();
    }

    public CustomOverlay getOverlay() {
        return overlay;
    }
}
