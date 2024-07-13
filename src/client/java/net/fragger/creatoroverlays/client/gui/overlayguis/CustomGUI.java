package net.fragger.creatoroverlays.client.gui.overlayguis;

import com.mojang.datafixers.util.Pair;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.fragger.creatoroverlays.client.gui.RootGUI;
import net.fragger.creatoroverlays.client.gui.AbstractGUI;
import net.fragger.creatoroverlays.client.overlays.CustomOverlay;
import net.fragger.creatoroverlays.util.config.COConfigs;
import net.minecraft.text.Text;

import static net.fragger.creatoroverlays.event.KeyInputHandler.coScreen;
import static net.fragger.creatoroverlays.util.config.COConfigs.CUSTOM_MODE;

public class CustomGUI extends AbstractGUI {

    public WPlainPanel customPanel = new WPlainPanel();

    private WToggleButton toggle;
    private WTextField filePath;
    private WButton set;
    private WButton cycleUp;
    private WButton cycleDown;
    private WSprite texture;

    private static CustomOverlay overlay = new CustomOverlay();

    public CustomGUI() {
        overlay.setRotation(CUSTOM_MODE);

        customPanel.setSize(RootGUI.guiWidth, RootGUI.guiHeight);

        WLabel head = new WLabel(Text.translatable("key.creatoroverlays.gui.custom"));
        head.setHorizontalAlignment(HorizontalAlignment.CENTER);
        customPanel.add(head, 120, 10);

        toggle = new WToggleButton(RootGUI.TOGGLE_ON, RootGUI.TOGGLE_OFF);
        customPanel.add(toggle, 10, y);
        toggle.setOnToggle(on -> {
            toggle();
        });

        /*filePath = new WTextField(Text.literal("File Path"));
        filePath.setMaxLength(512);
        customPanel.add(filePath, 38, y, 140, 18);

        set = new WButton(Text.literal("Set"));
        customPanel.add(set, 148, y, 30, 20);
        set.setOnClick(() -> {
           overlay.setTexture(filePath.getText());
        });*/

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
            COConfigs.writeConfigs();
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

    public void toggle(boolean display) {
        overlay.setisRendered(display);
        toggle.setToggle(display);
    }

    public void changeOverlayUp() {
        overlay.rotateUp();
        updateSprite();
        COConfigs.updateConfig(new Pair<>("custom.mode", overlay.rotation));
    }

    public void changeOverlayDown() {
        overlay.rotateDown();
        updateSprite();
        COConfigs.updateConfig(new Pair<>("custom.mode", overlay.rotation));
    }

    public CustomOverlay getOverlay() {
        return overlay;
    }
}
