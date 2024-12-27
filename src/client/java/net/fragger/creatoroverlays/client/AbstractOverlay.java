package net.fragger.creatoroverlays.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public abstract class AbstractOverlay implements HudRenderCallback {

    //Renders Overlay full screen. Requires Draw Context & Reference to Overlay Texture
    public static void render(DrawContext drawContext, Identifier texture) {
        int width = 0;
        int height = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            width = client.getWindow().getScaledWidth();
            height = client.getWindow().getScaledHeight();
        }
        render(drawContext, texture, 0, 0, width, height);
    }

    //Renders Overlay based off specified x and y, as well as width and height. Requires Draw Context, Reference to texture, x and y position, and texture width and height
    public static void render(DrawContext drawContext, Identifier texture, int x, int y, int width, int height) {
        drawContext.drawTexture(RenderLayer::getGuiTexturedOverlay, texture, x, y, 0, 0, width, height, width, height);

    }

}
