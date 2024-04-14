package net.fragger.creatoroverlays.client.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import net.fragger.creatoroverlays.client.StaticOverlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class AbstractGUI extends LightweightGuiDescription {

    public static final Identifier OUTLINE = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/overlay_outline.png");

    private static final Text black = Text.translatable("key.creatoroverlays.gui.color.black");
    private static final Text white = Text.translatable("key.creatoroverlays.gui.color.white");
    private static final Text red = Text.translatable("key.creatoroverlays.gui.color.red");

    public int y = 30;

    public Text getColor(StaticOverlay overlay) {
        if (overlay.color() == 1) {
            return white;
        } else if (overlay.color() == 2) {
            return red;
        } else {
            return black;
        }
    }
}
