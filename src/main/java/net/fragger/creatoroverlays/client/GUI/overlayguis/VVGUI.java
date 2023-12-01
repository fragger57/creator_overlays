package net.fragger.creatoroverlays.client.gui.overlayguis;

import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.fragger.creatoroverlays.client.gui.AbstractGUI;
import net.fragger.creatoroverlays.client.gui.OverlayGUI;
import net.fragger.creatoroverlays.client.overlays.VVOverlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.client.gui.RootGUI.*;
import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public class VVGUI extends AbstractGUI implements OverlayGUI {

    public static final Identifier Gridlines_ON = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/gridlines_on.png");
    public static final Identifier Gridlines_OFF = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/gridlines_off.png");
    public static final Identifier Gr_ON = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/gr_on.png");
    public static final Identifier Gr_OFF = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/gr_off.png");

    public WPlainPanel vvPanel = new WPlainPanel();
    private WToggleButton toggle;
    private WButton color;
    private WToggleButton gridlines;
    private WToggleButton gr;

    private static VVOverlay overlay = new VVOverlay();

    private WSprite texture = new WSprite(overlay.getTexture());

    public VVGUI() {

        vvPanel.setSize(guiWidth, guiHeight);

        WLabel head = new WLabel(Text.translatable("key.creatoroverlays.gui.verticalvideo"));
        head.setHorizontalAlignment(HorizontalAlignment.CENTER);
        vvPanel.add(head, 120, 10);

        toggle = new WToggleButton(TOGGLE_ON, TOGGLE_OFF);
        vvPanel.add(toggle, 10, y);
        toggle.setOnToggle(on -> {
            toggle();
        });

        color = new WButton(getColor(overlay));
        vvPanel.add(color, 38, y, 70, 20);
        color.setOnClick(() -> {
            changeColor();
        });

        gridlines = new WToggleButton(Gridlines_ON,  Gridlines_OFF);
        vvPanel.add(gridlines, 194, y);
        gridlines.setOnToggle(on -> {
            toggleGridlines();
        });

        gr = new WToggleButton(Gr_ON, Gr_OFF);
        vvPanel.add(gr, 222, y);
        gr.setOnToggle(on -> {
            toggleGR();
        });

        WSprite outline = new WSprite(OUTLINE);
        vvPanel.add(outline, 10, y * 2, 230, 131);

        updateSprite();

        WButton close = new WButton(Text.translatable("key.creatoroverlays.gui.close"));
        vvPanel.add(close, 200, 200, 40, 20);
        close.setOnClick(() -> {
            coScreen.close();
        });

    }

    public void toggle() {
        overlay.updateRenderStatus();
        toggle.setToggle(overlay.isRendered());
    }

    public void toggleGridlines() {
        if (overlay.getGRVV()){
            toggleGR();
        }
        overlay.setRO3VV(!overlay.getRO3VV());
        gridlines.setToggle(overlay.getRO3VV());
        updateSprite();
    }
    public void gridlinesOn() {
        overlay.setRO3VV(true);
        gridlines.setToggle(true);
        updateSprite();
    }
    public void gridlinesOff() {
        overlay.setRO3VV(false);
        gridlines.setToggle(false);
        updateSprite();
    }

    public void toggleGR() {
        if (overlay.getRO3VV()) {
            toggleGridlines();
        }
        overlay.setGRVV(!overlay.getGRVV());
        gr.setToggle(overlay.getGRVV());
        updateSprite();
    }
    public void grOn() {
        overlay.setGRVV(true);
        gr.setToggle(true);
        updateSprite();
    }
    public void grOff() {
        overlay.setGRVV(false);
        gr.setToggle(false);
        updateSprite();
    }

    public void updateSprite() {
        vvPanel.remove(texture);
        texture = new WSprite(overlay.getTexture());
        vvPanel.add(texture, 90, y * 2 + 2, 71, 127);
    }
    public void changeColor() {
        overlay.colorCycle();
        updateSprite();
        color.setLabel(getColor(overlay));
    }

    public VVOverlay getOverlay() {
        return overlay;
    }

}
