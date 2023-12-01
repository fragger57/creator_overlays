package net.fragger.creatoroverlays.client.gui.overlayguis;

import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.fragger.creatoroverlays.client.gui.AbstractGUI;
import net.fragger.creatoroverlays.client.gui.OverlayGUI;
import net.fragger.creatoroverlays.client.overlays.CamOverlay;
import net.minecraft.text.Text;

import static net.fragger.creatoroverlays.client.gui.RootGUI.*;
import static net.fragger.creatoroverlays.event.KeyInputHandler.coScreen;

public class CAMGUI extends AbstractGUI implements OverlayGUI {

    private static CamOverlay overlay = new CamOverlay();
    private WToggleButton toggle;
    private WButton color;
    private WButton side;
    private WSprite texture;

    public WPlainPanel camPanel = new WPlainPanel();

    public CAMGUI() {

        camPanel.setSize(guiWidth, guiHeight);

        WLabel head = new WLabel(Text.translatable("key.creatoroverlays.gui.cam"));
        head.setHorizontalAlignment(HorizontalAlignment.CENTER);
        camPanel.add(head, 120, 10);

        toggle = new WToggleButton(TOGGLE_ON, TOGGLE_OFF);
        camPanel.add(toggle, 10, y);
        toggle.setOnToggle(on -> {
            toggle();
        });

        color = new WButton(getColor(overlay));
        camPanel.add(color, 38, y, 70, 20);
        color.setOnClick(() -> {
            changeColor();
        });

        side = new WButton(Text.translatable("key.creatoroverlays.gui.side.left"));
        camPanel.add(side, 170, y, 70, 20);
        side.setOnClick(() -> {
            swapSides();
        });

        WSprite outline = new WSprite(OUTLINE);
        camPanel.add(outline, 10, y * 2, 230, 131);

        updateSprite();

        WButton close = new WButton(Text.translatable("key.creatoroverlays.gui.close"));
        camPanel.add(close, 200, 200, 40, 20);
        close.setOnClick(() -> {
            coScreen.close();
        });

    }

    public void updateSprite() {
        camPanel.remove(texture);
        texture = new WSprite(overlay.getTexture());
        camPanel.add(texture, 12, y * 2 + 2, 226, 127);
    }

    public void toggle() {
        overlay.updateRenderStatus();
        if (overlay.isRendered()) {
            toggle.setToggle(true);
        } else {
            toggle.setToggle(false);
        }
    }

    public void changeColor() {
        overlay.colorCycle();
        updateSprite();
        color.setLabel(getColor(overlay));
    }
    public void swapSides() {
        overlay.swap();
        updateSprite();
        if (overlay.state) {
            side.setLabel(Text.translatable("key.creatoroverlays.gui.side.left"));
        } else {
            side.setLabel(Text.translatable("key.creatoroverlays.gui.side.right"));
        }
    }

    public CamOverlay getOverlay() {
        return overlay;
    }
}
