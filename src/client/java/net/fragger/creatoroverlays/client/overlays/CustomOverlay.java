package net.fragger.creatoroverlays.client.overlays;

import com.mojang.datafixers.util.Pair;
import net.fragger.creatoroverlays.client.Overlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.fragger.creatoroverlays.util.config.COConfigs;
import net.fragger.creatoroverlays.client.StaticOverlay;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class CustomOverlay extends StaticOverlay implements Overlay {

    private static Identifier Custom01 = Identifier.of(creatoroverlays.MOD_ID,"textures/overlays/custom/custom01.png");
    private static Identifier Custom02 = Identifier.of(creatoroverlays.MOD_ID,"textures/overlays/custom/custom02.png");
    private static Identifier Custom03 = Identifier.of(creatoroverlays.MOD_ID,"textures/overlays/custom/custom03.png");
    private static Identifier Custom04 = Identifier.of(creatoroverlays.MOD_ID,"textures/overlays/custom/custom04.png");

    private boolean isRendered = false;

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickDelta) {
        if (isRendered) {
            render(drawContext, getTexture());
        }
    }

    public void updateRenderStatus() {
        isRendered = !isRendered;
        setisRendered(isRendered);
    }

    public Identifier getTexture() {
        Identifier texture = Custom01;
        if (rotation == 1) {
            texture = Custom02;
        } else if (rotation == 2) {
            texture = Custom03;
        } else if (rotation == 3) {
            texture = Custom04;
        } else {
            texture = Custom01;
        }
        return texture;
    }

    public boolean isRendered() {
        return isRendered;
    }

    public void setisRendered(boolean rendered) {
        isRendered = rendered;
        COConfigs.updateConfig(new Pair<>("display.custom", isRendered));
    }
    public void setRotation(int r) {
        this.rotation = r;
    }
}
