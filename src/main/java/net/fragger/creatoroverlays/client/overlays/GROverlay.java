package net.fragger.creatoroverlays.client.overlays;

import net.fragger.creatoroverlays.client.Overlay;
import net.fragger.creatoroverlays.client.StaticOverlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public class GROverlay extends StaticOverlay implements Overlay {
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

    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if (isRendered) {
            render(drawContext, getTexture());
        }
    }
    public void updateRenderStatus() {
        if (!isRendered) {
            if (ro3Overlay.isRendered()) {
                ro3GUI.toggle();
                vvGUI.gridlinesOff();
            }
            isRendered = true;
        } else {
            isRendered = false;
        }
    }
    public Identifier getTexture() {
        Identifier texture = GR_Overlay;
        if (color == 1) {
            if (rotation == 0) {
                texture = GR_Overlay_White;
            } else if (rotation == 90) {
                texture = GR_Overlay_White_90;
            } else if (rotation == 180) {
                texture = GR_Overlay_White_180;
            } else {
                texture = GR_Overlay_White_270;
            }
        } else if (color == 2){
            if (rotation == 0) {
                texture = GR_Overlay_Red;
            } else if (rotation == 90) {
                texture = GR_Overlay_Red_90;
            } else if (rotation == 180) {
                texture = GR_Overlay_Red_180;
            } else {
                texture = GR_Overlay_Red_270;
            }
        } else {
            if (rotation == 0) {
                texture = GR_Overlay;
            } else if (rotation == 90) {
                texture = GR_Overlay_90;
            } else if (rotation == 180) {
                texture = GR_Overlay_180;
            } else {
                texture = GR_Overlay_270;
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
    public int getRotation() {
        return rotation;
    }
}
