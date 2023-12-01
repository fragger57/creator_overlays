package net.fragger.creatoroverlays.client.overlays;

import net.fragger.creatoroverlays.client.Overlay;
import net.fragger.creatoroverlays.client.StaticOverlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public class VVOverlay extends StaticOverlay implements Overlay {
    //blank vertical video
    private static final Identifier VV_Overlay = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/vertical_video/vv_overlay.png");
    private static final Identifier VV_Overlay_White = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/vertical_video/vv_overlay_white.png");
    private static final Identifier VV_Overlay_Red = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/vertical_video/vv_overlay_red.png");

    //ro3 vertical video
    private static final Identifier VV_OVERLAY_RO3 = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/rule_of_thirds/vv_overlay_ro3.png");
    private static final Identifier VV_Overlay_White_RO3 = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/vertical_video/rule_of_thirds/vv_overlay_white_ro3.png");
    private static final Identifier VV_Overlay_Red_RO3 = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/vertical_video/rule_of_thirds/vv_overlay_red_ro3.png");

    //gr vertical video
    private static final Identifier VV_OVERLAY_GR = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_gr.png");
    private static final Identifier VV_OVERLAY_GR_90 = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_gr_90.png");
    private static final Identifier VV_OVERLAY_GR_180 = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_gr_180.png");
    private static final Identifier VV_OVERLAY_GR_270 = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_gr_270.png");
    private static final Identifier VV_OVERLAY_White_GR = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_white_gr.png");
    private static final Identifier VV_OVERLAY_White_GR_90 = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_white_gr_90.png");
    private static final Identifier VV_OVERLAY_White_GR_180 = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_white_gr_180.png");
    private static final Identifier VV_OVERLAY_White_GR_270 = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_white_gr_270.png");
    private static final Identifier VV_OVERLAY_Red_GR = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_red_gr.png");
    private static final Identifier VV_OVERLAY_Red_GR_90 = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_red_gr_90.png");
    private static final Identifier VV_OVERLAY_Red_GR_180 = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_red_gr_180.png");
    private static final Identifier VV_OVERLAY_Red_GR_270 = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/vertical_video/golden_ratio/vv_overlay_red_gr_270.png");

    private static boolean isRendered = false;
    public static boolean isRO3VV = false;
    public static boolean isGRVV = false;

    private static int x = 0;
    private static int y = 0;
    private static int width = 0;
    private static int height = 0;

    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            height = client.getWindow().getScaledHeight();
            width = height / 16 * 9 + 10;
            x = client.getWindow().getScaledWidth() / 2 - (width / 2);
        }
        if (isRendered) {
            render(drawContext, getTexture(), x, y, width, height);
        }
    }
    public void updateRenderStatus() {
        isRendered = !isRendered;
        if (isRendered()) {
            if (ro3Overlay.isRendered()) {
                ro3Overlay.setisRendered(false);
                if (grOverlay.isRendered()) {
                    grOverlay.setisRendered(false);
                }
                if (camOverlay.isRendered()) {
                    camGUI.toggle();
                }
            } else {
                if (isRO3VV) {
                    ro3Overlay.setisRendered(true);
                } else if (isGRVV) {
                    grOverlay.setisRendered(true);
                }
            }
        }
    }

    public Identifier getTexture() {
        Identifier texture = VV_Overlay;
        //normal overlay
        if (!ro3Overlay.isRendered() & !grOverlay.isRendered()) {
            if (color == 1) {
                texture = VV_Overlay_White;
            } else if (color == 2){
                texture = VV_Overlay_Red;
            } else {
                texture = VV_Overlay;
            }
        }

        //ro3 overlay
        if (isRO3VV) {
            if (color == 1) {
                texture = VV_Overlay_White_RO3;
            } else if (color == 2) {
                texture = VV_Overlay_Red_RO3;
            } else {
                texture = VV_OVERLAY_RO3;
            }
        }
        //renders vv overlay with gr overlay
        if (isGRVV) {
            if (color == 1) {
                if(grOverlay.getRotation() == 0) {
                    texture = VV_OVERLAY_White_GR;
                } else if (grOverlay.getRotation() == 90) {
                    texture = VV_OVERLAY_White_GR_90;
                } else if (grOverlay.getRotation() == 180) {
                    texture = VV_OVERLAY_White_GR_180;
                } else if (grOverlay.getRotation() == 270) {
                    texture = VV_OVERLAY_White_GR_270;
                }
            } else if (color == 2) {
                if (grOverlay.getRotation() == 0) {
                    texture = VV_OVERLAY_Red_GR;
                } else if (grOverlay.getRotation() == 90) {
                    texture = VV_OVERLAY_Red_GR_90;
                } else if (grOverlay.getRotation() == 180) {
                    texture = VV_OVERLAY_Red_GR_180;
                } else if (grOverlay.getRotation() == 270) {
                    texture = VV_OVERLAY_Red_GR_270;
                }
            } else {
                if(grOverlay.getRotation() == 0) {
                    texture = VV_OVERLAY_GR;
                } else if (grOverlay.getRotation() == 90) {
                    texture = VV_OVERLAY_GR_90;
                } else if (grOverlay.getRotation() == 180) {
                    texture = VV_OVERLAY_GR_180;
                } else if (grOverlay.getRotation() == 270) {
                    texture = VV_OVERLAY_GR_270;
                }
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
    public boolean getRO3VV(){
        return isRO3VV;
    }
    public boolean getGRVV() {
        return isGRVV;
    }
    public void setRO3VV(boolean state) {
        isRO3VV = state;
    }
    public void setGRVV(boolean state) {
        isGRVV = state;
    }
}
