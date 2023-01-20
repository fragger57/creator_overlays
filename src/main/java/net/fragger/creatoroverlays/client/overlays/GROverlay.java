package net.fragger.creatoroverlays.client.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fragger.creatoroverlays.client.OverlayHelper;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public class GROverlay extends OverlayHelper implements HudRenderCallback {
    private static final Identifier GR_Overlay = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay.png");
    private static final Identifier GR_Overlay_90 = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay_90.png");
    private static final Identifier GR_Overlay_180 = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay_180.png");
    private static final Identifier GR_Overlay_270 = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay_270.png");
    private static final Identifier GR_Overlay_White = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay_white.png");
    private static final Identifier GR_Overlay_White_90 = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay_white_90.png");
    private static final Identifier GR_Overlay_White_180 = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay_white_180.png");
    private static final Identifier GR_Overlay_White_270 = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay_white_270.png");
    private static final Identifier GR_Overlay_Red = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay_red.png");
    private static final Identifier GR_Overlay_Red_90 = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay_red_90.png");
    private static final Identifier GR_Overlay_Red_180 = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay_red_180.png");
    private static final Identifier GR_Overlay_Red_270 = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/golden_ratio/gr_overlay_red_270.png");

    private static boolean isRendered = false;

    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        if (isRendered) {
           if(color == 0){
               if (rotation == 0) {
                   render(matrixStack, GR_Overlay);
               } else if (rotation == 90) {
                   render(matrixStack, GR_Overlay_90);
               } else if (rotation == 180) {
                   render(matrixStack, GR_Overlay_180);
               } else {
                   render(matrixStack, GR_Overlay_270);
               }
           } else if (color == 1) {
               if (rotation == 0) {
                   render(matrixStack, GR_Overlay_White);
               } else if (rotation == 90) {
                   render(matrixStack, GR_Overlay_White_90);
               } else if (rotation == 180) {
                   render(matrixStack, GR_Overlay_White_180);
               } else {
                   render(matrixStack, GR_Overlay_White_270);
               }
           } else if (color == 2){
               if (rotation == 0) {
                   render(matrixStack, GR_Overlay_Red);
               } else if (rotation == 90) {
                   render(matrixStack, GR_Overlay_Red_90);
               } else if (rotation == 180) {
                   render(matrixStack, GR_Overlay_Red_180);
               } else {
                   render(matrixStack, GR_Overlay_Red_270);
               }
           }
        } else {
            RenderSystem.disableTexture();
        }
    }
    public void updateRenderStatus() {
        if (!isRendered) {
            if (ro3Overlay.isRendered()) {
                ro3Overlay.setisRendered(false);
            }
            isRendered = true;
        } else {
            isRendered = false;
        }
        HudRenderCallback.EVENT.register(this);
        HudRenderCallback.EVENT.register(ro3Overlay);
    }
    public boolean isRendered() {
        return isRendered;
    }
    public void setisRendered(boolean Rendered) {
        isRendered = Rendered;
    }
}
