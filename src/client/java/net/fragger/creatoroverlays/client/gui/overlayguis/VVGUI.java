package net.fragger.creatoroverlays.client.gui.overlayguis;

import com.mojang.datafixers.util.Pair;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.fragger.creatoroverlays.client.gui.AbstractGUI;
import net.fragger.creatoroverlays.client.gui.OverlayGUI;
import net.fragger.creatoroverlays.client.overlays.VVOverlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.fragger.creatoroverlays.util.config.COConfigs;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.client.gui.RootGUI.*;
import static net.fragger.creatoroverlays.event.KeyInputHandler.*;
import static net.fragger.creatoroverlays.util.config.COConfigs.COLOR_MODE;
import static net.fragger.creatoroverlays.util.config.COConfigs.VV_COLOR;

public class VVGUI extends AbstractGUI implements OverlayGUI {

    public static final Identifier Gridlines_ON = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/gridlines_on.png");
    public static final Identifier Gridlines_OFF = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/gridlines_off.png");

    public WPlainPanel vvPanel = new WPlainPanel();
    private WToggleButton toggle;
    private WButton color;
    private WToggleButton gridlines;

    private static VVOverlay overlay = new VVOverlay();

    private WSprite texture = new WSprite(overlay.getTexture());

    public VVGUI() {
        if(COLOR_MODE == 1) {
            overlay.setColor(VV_COLOR);
        }

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
        vvPanel.add(color, 38, y, 95, 20);
        color.setOnClick(() -> {
            changeColor();
        });

        gridlines = new WToggleButton(Gridlines_ON,  Gridlines_OFF);
        vvPanel.add(gridlines, 222, y);
        gridlines.setOnToggle(on -> {
            toggleGridlines();
        });

        WSprite outline = new WSprite(OUTLINE);
        vvPanel.add(outline, 10, y * 2, 230, 131);

        updateSprite();

        WButton close = new WButton(Text.translatable("key.creatoroverlays.gui.close"));
        vvPanel.add(close, 200, 200, 40, 20);
        close.setOnClick(() -> {
            coScreen.close();
            COConfigs.writeConfigs();
        });

    }

    public void toggle() {
        overlay.updateRenderStatus();
        toggle.setToggle(overlay.isRendered());
    }

    public void toggle(boolean display) {
        overlay.setisRendered(display);
        toggle.setToggle(display);
    }

    public void toggleGridlines() {
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

    public void updateSprite() {
        vvPanel.remove(texture);
        texture = new WSprite(overlay.getTexture());
        vvPanel.add(texture, 90, y * 2 + 2, 71, 127);
    }
    public void changeColor() {
        overlay.colorCycle();
        updateSprite();
        color.setLabel(getColor(overlay));
        COConfigs.updateConfig(new Pair<>("vv.color", overlay.color));
    }

    public VVOverlay getOverlay() {
        return overlay;
    }

}
