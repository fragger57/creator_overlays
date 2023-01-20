package net.fragger.creatoroverlays.client.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fragger.creatoroverlays.client.OverlayHelper;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public class RO3Overlay extends OverlayHelper implements HudRenderCallback {
    private static final Identifier RO3_Overlay = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/rule_of_thirds/ro3_overlay.png");
    private static final Identifier RO3_Overlay_White = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/rule_of_thirds/ro3_overlay_white.png");
    private static final Identifier RO3_Overlay_Red = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/rule_of_thirds/ro3_overlay_red.png");

    MinecraftClient client = MinecraftClient.getInstance();

    private static boolean isRendered = false;

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        if (isRendered) {
            if (color == 0) {
                render(matrixStack, RO3_Overlay);
            } else if (color == 1) {
                render(matrixStack, RO3_Overlay_White);
            } else if (color == 2) {
                render(matrixStack, RO3_Overlay_Red);
            }
        } else {
            RenderSystem.disableTexture();
        }
    }
    public void updateRenderStatus() {
        if (!isRendered) {
            if (grOverlay.isRendered()) {
                grOverlay.setisRendered(false);
            }
            isRendered = true;
        } else {
            isRendered = false;
        }
        HudRenderCallback.EVENT.register(this);
        HudRenderCallback.EVENT.register(grOverlay);
    }
    public boolean isRendered() {
        return isRendered;
    }
    public void setisRendered(boolean Rendered) {
        isRendered = Rendered;
    }
}
