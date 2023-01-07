package net.fragger.creatoroverlays.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public abstract class OverlayHelper {

   public boolean isRendered = false;
   public static int color = 0;
   public static int rotation = 0;

   public OverlayHelper(){

   }
   //Renders Overlay full screen. Requires MatrixStack and Reference to Overlay Texture
   public static void render(MatrixStack matrixStack, Identifier texture) {
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
       DrawableHelper.drawTexture(matrixStack, 0, 0, 0, 0, width, height, width, height);
   }
    //Renders Overlay based off specified x and y, as well as width and height. Requires MatrixStack, Reference to texture, x and y position, and texture width and height
   public static void render(MatrixStack matrixStack, Identifier texture, int x, int y, int width, int height) {

       RenderSystem.setShader(GameRenderer::getPositionTexShader);
       RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
       RenderSystem.setShaderTexture(0, texture);
       DrawableHelper.drawTexture(matrixStack, x, y, 0, 0, width, height, width, height);
   }
   public static void colorCycle() {
       if (color == 0) {
           color = 1;
       } else if (color == 1) {
           color = 2;
       } else if (color == 2) {
           color = 0;
       }
       HudRenderCallback.EVENT.register(ro3Overlay);
       HudRenderCallback.EVENT.register(grOverlay);
       HudRenderCallback.EVENT.register(vvOverlay);
   }
   public static void rotateUp() {
       if (rotation == 0) {
           rotation = 90;
       } else if (rotation == 90) {
           rotation = 180;
       } else if (rotation == 180) {
           rotation = 270;
       } else {
           rotation = 0;
       }
       HudRenderCallback.EVENT.register(grOverlay);
       HudRenderCallback.EVENT.register(vvOverlay);
   }
   public static void rotateDown() {
       if (rotation == 180) {
           rotation = 90;
       } else if (rotation == 270) {
           rotation = 180;
       } else if (rotation == 0) {
           rotation = 270;
       } else {
           rotation = 0;
       }
       HudRenderCallback.EVENT.register(grOverlay);
       HudRenderCallback.EVENT.register(vvOverlay);
   }
}
