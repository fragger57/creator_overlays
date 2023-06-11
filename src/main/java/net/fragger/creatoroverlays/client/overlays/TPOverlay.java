package net.fragger.creatoroverlays.client.overlays;

import net.fragger.creatoroverlays.creatoroverlays;
import net.fragger.creatoroverlays.util.MoveableOverlay;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class TPOverlay extends MoveableOverlay {

    private static final Identifier TP_Overlay = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay.png");
    private static final Identifier TP_Overlay_White = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_white.png");
    private static final Identifier TP_Overlay_Red = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_red.png");
    private static final Identifier TP_Overlay_Blue = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_blue.png");
    private static final Identifier TP_Overlay_Selected = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_selected.png");
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if(isRendered) {
            if (mColor == 0) {
                render(drawContext, TP_Overlay, x, y, width, height);
            } else if (mColor == 1) {
                render(drawContext, TP_Overlay_White, x, y, width, height);
            } else if (mColor == 2) {
                render(drawContext, TP_Overlay_Red, x, y, width, height);
            } else if (mColor == 3) {
                render(drawContext, TP_Overlay_Blue, x, y, width, height);
            }
            if (isSelected) {
                render(drawContext, TP_Overlay_Selected, x, y, width, height);
            }
        }
    }
    public void initializeTPOverlay() {
        height = client.getWindow().getScaledHeight() / 17;
        width = height;
        reset();
    }
    public void updateRenderStatus() {
        isRendered = !isRendered;
        renderStatus = !renderStatus;
        isSelected = true;
    }
    public void updateSelected() {
        isSelected = !isSelected;
    }
    public boolean isRendered() {
        return isRendered;
    }
    public void setIsRendered(boolean Rendered) {
        isRendered = Rendered;
    }
    public boolean renderStatus() {
        return renderStatus;
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

    public void setRenderStatus(boolean status) {
        renderStatus = status;
    }
}
