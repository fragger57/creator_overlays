package net.fragger.creatoroverlays.client.overlays;

import net.fragger.creatoroverlays.creatoroverlays;
import net.fragger.creatoroverlays.util.StaticOverlay;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.grOverlay;

public class RO3Overlay extends StaticOverlay {
    private static final Identifier RO3_Overlay = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/rule_of_thirds/ro3_overlay.png");
    private static final Identifier RO3_Overlay_White = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/rule_of_thirds/ro3_overlay_white.png");
    private static final Identifier RO3_Overlay_Red = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/rule_of_thirds/ro3_overlay_red.png");

    private static boolean isRendered = false;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if (isRendered) {
            if (color == 1) {
                render(drawContext, RO3_Overlay_White);
            } else if (color == 2) {
                render(drawContext, RO3_Overlay_Red);
            } else {
                render(drawContext, RO3_Overlay);
            }
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
    }
    public boolean isRendered() {
        return isRendered;
    }
    public void setisRendered(boolean Rendered) {
        isRendered = Rendered;
    }

}
