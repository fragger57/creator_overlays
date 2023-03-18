package net.fragger.creatoroverlays.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public abstract class Overlay implements HudRenderCallback {

    //Renders Overlay full screen. Requires MatrixStack and Reference to Overlay Texture
    public static void render(MatrixStack matrixStack, Identifier texture) {
        int width = 0;
        int height = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            width = client.getWindow().getScaledWidth();
            height = client.getWindow().getScaledHeight();
        }

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        DrawableHelper.drawTexture(matrixStack, 0, 0, 0, 0, width, height, width, height);
    }

    //Renders Overlay based off specified x and y, as well as width and height. Requires MatrixStack, Reference to texture, x and y position, and texture width and height
    public static void render(MatrixStack matrixStack, Identifier texture, int x, int y, int width, int height) {

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        DrawableHelper.drawTexture(matrixStack, x, y, 0, 0, width, height, width, height);
    }
}
