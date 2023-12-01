package net.fragger.creatoroverlays.client.gui.overlayguis;

import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.fragger.creatoroverlays.client.gui.AbstractGUI;
import net.fragger.creatoroverlays.client.gui.OverlayGUI;
import net.fragger.creatoroverlays.client.overlays.RO3Overlay;
import net.minecraft.text.Text;

import static net.fragger.creatoroverlays.client.gui.RootGUI.*;
import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public class RO3GUI extends AbstractGUI implements OverlayGUI {

    private static RO3Overlay overlay = new RO3Overlay();

    public WPlainPanel ro3Panel = new WPlainPanel();

    private WToggleButton toggle;
    private WButton color;
    private WButton type;
    private WSprite texture;

    public RO3GUI() {

        ro3Panel.setSize(guiWidth, guiHeight);

        WLabel head = new WLabel(Text.translatable("key.creatoroverlays.gui.gridlines"));
        head.setHorizontalAlignment(HorizontalAlignment.CENTER);
        ro3Panel.add(head, 120, 10);

        toggle = new WToggleButton(TOGGLE_ON, TOGGLE_OFF);
        ro3Panel.add(toggle, 10, y);
        toggle.setOnToggle(on -> {
            toggle();
        });

        color = new WButton(getColor(overlay));
        ro3Panel.add(color, 38, y, 70, 20);
        color.setOnClick(() -> {
            changeColor();
        });

        type = new WButton(Text.translatable("key.creatoroverlays.gui.gridlines.thirds"));
        ro3Panel.add(type, 170, y, 70, 20);
        type.setOnClick(() -> {
           changeGrid();
        });

        WSprite outline = new WSprite(OUTLINE);
        ro3Panel.add(outline, 10, y * 2, 230, 131);

        updateSprite();

        WButton close = new WButton(Text.translatable("key.creatoroverlays.gui.close"));
        ro3Panel.add(close, 200, 200, 40, 20);
        close.setOnClick(() -> {
            coScreen.close();
        });
    }

    public void updateSprite() {
        ro3Panel.remove(texture);
        texture = new WSprite(overlay.getTexture());
        ro3Panel.add(texture, 12, y * 2 + 2, 226, 127);
    }

    public void toggle() {
        overlay.updateRenderStatus();
        toggle.setToggle(overlay.isRendered());
        if (overlay.isRendered()) {
            vvGUI.gridlinesOn();
        } else {
            vvGUI.gridlinesOff();
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

    public void changeGrid() {
        overlay.swap();
        updateSprite();
        if (overlay.state) {
            type.setLabel(Text.translatable("key.creatoroverlays.gui.gridlines.thirds"));
        } else {
            type.setLabel(Text.translatable("key.creatoroverlays.gui.gridlines.quarters"));
        }
    }

    public RO3Overlay getOverlay() {
        return overlay;
    }
}
