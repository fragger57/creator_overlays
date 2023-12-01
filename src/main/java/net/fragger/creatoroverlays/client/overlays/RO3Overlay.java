package net.fragger.creatoroverlays.client.overlays;

import net.fragger.creatoroverlays.client.Overlay;
import net.fragger.creatoroverlays.client.StaticOverlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public class RO3Overlay extends StaticOverlay implements Overlay {
    private static final Identifier RO3_Overlay = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/gridlines/ro3_overlay.png");
    private static final Identifier RO3_Overlay_White = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/gridlines/ro3_overlay_white.png");
    private static final Identifier RO3_Overlay_Red = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/gridlines/ro3_overlay_red.png");
    private static final Identifier Quarter_Overlay = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/gridlines/quarter_overlay.png");
    private static final Identifier Quarter_Overlay_White = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/gridlines/quarter_overlay_white.png");
    private static final Identifier Quarter_Overlay_Red = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/gridlines/quarter_overlay_red.png");

    private static boolean isRendered = false;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if (isRendered) {
            render(drawContext,getTexture());
        }
    }
    public void updateRenderStatus() {
        if (!isRendered) {
            if (grOverlay.isRendered()) {
                grGUI.toggle();
                vvGUI.grOff();
            }
        }
        isRendered = !isRendered;
    }

    public Identifier getTexture() {
        Identifier texture = RO3_Overlay;
        if (state) {
            if (color == 1) {
                texture = RO3_Overlay_White;
            } else if (color == 2) {
                texture = RO3_Overlay_Red;
            } else {
                texture = RO3_Overlay;
            }
        } else {
            if (color == 1) {
                texture = Quarter_Overlay_White;
            } else if (color == 2) {
                texture = Quarter_Overlay_Red;
            } else {
                texture = Quarter_Overlay;
            }
        }
        return texture;
    }

    public boolean isRendered() {
        return isRendered;
    }
    public void setisRendered(boolean Rendered) {
        isRendered = Rendered;
    }

}
