package net.fragger.creatoroverlays.client.overlays;

import net.fragger.creatoroverlays.client.MoveableOverlay;
import net.fragger.creatoroverlays.client.Overlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class TPOverlay extends MoveableOverlay implements Overlay {

    private static final Identifier TP_Overlay = Identifier.of(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay.png");
    private static final Identifier TP_Overlay_White = Identifier.of(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_white.png");
    private static final Identifier TP_Overlay_Red = Identifier.of(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_red.png");
    private static final Identifier TP_Overlay_Blue = Identifier.of(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_blue.png");
    private static final Identifier TP_Overlay_Selected = Identifier.of(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_selected.png");
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickDelta) {
        if(isRendered) {
                render(drawContext, getTexture(), x, y, width, height);
            if (isSelected) {
                render(drawContext, TP_Overlay_Selected, x, y, width, height);
            }
        }
    }
    public void initialize() {
        height = client.getWindow().getScaledHeight() / 17;
        width = height;
        reset();
        isRendered = true;
        invisible = false;
        isSelected = true;
        EVENT.register(this);
    }
    public void updateRenderStatus() {
        invisible = isRendered;
        isRendered = !isRendered;
        isSelected = true;
    }

    public Identifier getTexture() {
        Identifier texture = TP_Overlay;
        if (mColor == 0) {
            texture = TP_Overlay;
        } else if (mColor == 1) {
            texture = TP_Overlay_White;
        } else if (mColor == 2) {
            texture = TP_Overlay_Red;
        } else if (mColor == 3) {
            texture = TP_Overlay_Blue;
        }
        return texture;
    }
    public void updateSelected() {
        isSelected = !isSelected;
    }
    public boolean isRendered() {
        return isRendered;
    }
    public void setisRendered(boolean Rendered) {
        isRendered = Rendered;
    }
    public boolean invisible() {
        return invisible;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setIsSelected(boolean Selected) {
        isSelected = Selected;
    }
    public int color() {
        return mColor;
    }
    public void setColor(int color) {
        mColor = color;
    }
    public void setInvisible(boolean status) {
        invisible = status;
    }
}
