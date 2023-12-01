package net.fragger.creatoroverlays.client.gui.overlayguis;

import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.fragger.creatoroverlays.client.gui.AbstractGUI;
import net.fragger.creatoroverlays.client.gui.OverlayGUI;
import net.fragger.creatoroverlays.client.overlays.GROverlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.client.gui.RootGUI.*;
import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public class GRGUI extends AbstractGUI implements OverlayGUI {

    public static final Identifier ROTATE_Up = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/c_rotate_arrow.png");
    public static final Identifier ROTATE_Down = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/cc_rotate_arrow.png");

    public WPlainPanel grPanel = new WPlainPanel();

    private WToggleButton toggle;
    private WButton color;
    private WButton rotateUp;
    private WButton rotateDown;
    private WSprite texture;

    private static GROverlay overlay = new GROverlay();

    public GRGUI() {

        grPanel.setSize(guiWidth, guiHeight);

        WLabel head = new WLabel(Text.translatable("key.creatoroverlays.gui.goldenratio"));
        head.setHorizontalAlignment(HorizontalAlignment.CENTER);
        grPanel.add(head, 120, 10);

        toggle = new WToggleButton(TOGGLE_ON, TOGGLE_OFF);
        grPanel.add(toggle, 10, y);
        toggle.setOnToggle(on -> {
            toggle();
        });

        color = new WButton(getColor(overlay));
        grPanel.add(color, 38, y, 70, 20);
        color.setOnClick(() -> {
            changeColor();
        });

        rotateUp = new WButton(new TextureIcon(ROTATE_Up));
        grPanel.add(rotateUp, 190, y, 20, 20);
        rotateUp.setOnClick(() -> {
            rotateUp();
        });

        rotateDown = new WButton(new TextureIcon(ROTATE_Down));
        grPanel.add(rotateDown, 220, y, 20, 20);
        rotateDown.setOnClick(() -> {
            rotateDown();
        });

        WSprite outline = new WSprite(OUTLINE);
        grPanel.add(outline, 10, y * 2, 230, 131);

        updateSprite();

        WButton close = new WButton(Text.translatable("key.creatoroverlays.gui.close"));
        grPanel.add(close, 200, 200, 40, 20);
        close.setOnClick(() -> {
            coScreen.close();
        });
    }

    public void updateSprite() {
        grPanel.remove(texture);
        texture = new WSprite(overlay.getTexture());
        grPanel.add(texture, 12, y * 2 + 2, 226, 127);
    }

    public void toggle() {
        overlay.updateRenderStatus();
        toggle.setToggle(overlay.isRendered());
        if (overlay.isRendered()) {
            vvGUI.grOn();
        } else {
            vvGUI.grOff();
        }
        if (vvOverlay.isRendered()) {
            overlay.setisRendered(false);
        }
    }

    public void changeColor() {
        overlay.colorCycle();
        updateSprite();
        color.setLabel(getColor(overlay));
    }

    public void rotateUp() {
        overlay.rotateUp();
        updateSprite();
    }

    public void rotateDown() {
        overlay.rotateDown();
        updateSprite();
    }

    public GROverlay getOverlay() {
        return overlay;
    }
}
