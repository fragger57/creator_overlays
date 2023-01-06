package net.fragger.creatoroverlays.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.client.RenderHandler.*;

public class GROverlay implements HudRenderCallback {
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

    public void onHudRender(MatrixStack matrixStack, float tickDelta) {

        if (isGRRendered) {
           if(color == 0){
               if (rotation == 0) {
                   this.render(matrixStack, GR_Overlay);
               } else if (rotation == 90) {
                   this.render(matrixStack, GR_Overlay_90);
               } else if (rotation == 180) {
                   this.render(matrixStack, GR_Overlay_180);
               } else {
                   this.render(matrixStack, GR_Overlay_270);
               }
           } else if (color == 1) {
               if (rotation == 0) {
                   this.render(matrixStack, GR_Overlay_White);
               } else if (rotation == 90) {
                   this.render(matrixStack, GR_Overlay_White_90);
               } else if (rotation == 180) {
                   this.render(matrixStack, GR_Overlay_White_180);
               } else {
                   this.render(matrixStack, GR_Overlay_White_270);
               }
           } else if (color == 2){
               if (rotation == 0) {
                   this.render(matrixStack, GR_Overlay_Red);
               } else if (rotation == 90) {
                   this.render(matrixStack, GR_Overlay_Red_90);
               } else if (rotation == 180) {
                   this.render(matrixStack, GR_Overlay_Red_180);
               } else {
                   this.render(matrixStack, GR_Overlay_Red_270);
               }
           }
        } else {
            RenderSystem.disableTexture();
        }
    }
    private void render(MatrixStack matrixStack, Identifier texture){
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            width = client.getWindow().getScaledWidth();
            height = client.getWindow().getScaledHeight();
        }
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        DrawableHelper.drawTexture(matrixStack, x, y, 0, 0, width, height, width, height);
    }
}
