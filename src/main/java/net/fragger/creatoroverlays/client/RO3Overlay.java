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

public class RO3Overlay implements HudRenderCallback {
    private static final Identifier RO3_Overlay = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/rule_of_thirds/ro3_overlay.png");
    private static final Identifier RO3_Overlay_White = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/rule_of_thirds/ro3_overlay_white.png");
    private static final Identifier RO3_Overlay_Red = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/rule_of_thirds/ro3_overlay_red.png");

    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        if (isRO3Rendered) {
            if (color == 0) {
                this.render(matrixStack, RO3_Overlay);
            } else if (color == 1) {
                this.render(matrixStack, RO3_Overlay_White);
            } else if (color == 2) {
                this.render(matrixStack, RO3_Overlay_Red);
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
