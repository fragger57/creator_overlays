package net.fragger.creatoroverlays.client.overlays;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fragger.creatoroverlays.creatoroverlays;
import net.fragger.creatoroverlays.util.StaticOverlay;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.grOverlay;
import static net.fragger.creatoroverlays.event.KeyInputHandler.ro3Overlay;

public class VVOverlay extends StaticOverlay implements HudRenderCallback {
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

    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            height = client.getWindow().getScaledHeight();
            width = height / 16 * 9 + 10;
            x = client.getWindow().getScaledWidth() / 2 - (width / 2);
        }
        if (isRendered) {
            //renders vv overlay normally
            if (!ro3Overlay.isRendered() & !grOverlay.isRendered()) {
                if (color == 0) {
                    render(matrixStack, VV_Overlay, x, y, width, height);
                } else if (color == 1) {
                    render(matrixStack, VV_Overlay_White, x, y, width, height);
                } else if (color == 2){
                    render(matrixStack, VV_Overlay_Red, x, y, width, height);
                }
            }
            //renders vv overlay with ro3 overlay
            if (isRO3VV) {
                if (color == 0) {
                    render(matrixStack, VV_OVERLAY_RO3, x, y, width, height);
                } else if (color == 1) {
                    render(matrixStack, VV_Overlay_White_RO3, x, y, width, height);
                } else if (color == 2) {
                    render(matrixStack, VV_Overlay_Red_RO3, x, y, width, height);
                }
            }
            //renders vv overlay with gr overlay
            if (isGRVV) {
                if (color == 0) {
                    if(rotation == 0) {
                        render(matrixStack, VV_OVERLAY_GR, x, y, width, height);
                    } else if (rotation == 90) {
                        render(matrixStack, VV_OVERLAY_GR_90, x, y, width, height);
                    } else if (rotation == 180) {
                        render(matrixStack, VV_OVERLAY_GR_180, x, y, width, height);
                    } else if (rotation == 270) {
                        render(matrixStack, VV_OVERLAY_GR_270, x, y, width, height);
                    }
                } else if (color == 1) {
                    if(rotation == 0) {
                        render(matrixStack, VV_OVERLAY_White_GR, x, y, width, height);
                    } else if (rotation == 90) {
                        render(matrixStack, VV_OVERLAY_White_GR_90, x, y, width, height);
                    } else if (rotation == 180) {
                        render(matrixStack, VV_OVERLAY_White_GR_180, x, y, width, height);
                    } else if (rotation == 270) {
                        render(matrixStack, VV_OVERLAY_White_GR_270, x, y, width, height);
                    }
                } else if (color == 2) {
                    if (rotation == 0) {
                        render(matrixStack, VV_OVERLAY_Red_GR, x, y, width, height);
                    } else if (rotation == 90) {
                        render(matrixStack, VV_OVERLAY_Red_GR_90, x, y, width, height);
                    } else if (rotation == 180) {
                        render(matrixStack, VV_OVERLAY_Red_GR_180, x, y, width, height);
                    } else if (rotation == 270) {
                        render(matrixStack, VV_OVERLAY_Red_GR_270, x, y, width, height);
                    }
                }
            }
        } else {
            //RenderSystem.disableTexture();
        }
    }
    public void updateRenderStatus() {
        if (!isRendered) {
            if (ro3Overlay.isRendered()) {
                ro3Overlay.setisRendered(false);
                isRO3VV = true;
            }
            if (grOverlay.isRendered()) {
                grOverlay.setisRendered(false);
                isGRVV = true;
            }
            isRendered = true;
        } else {
            if (isRO3VV) {
                ro3Overlay.setisRendered(true);
                isRO3VV = false;
            }
            if (isGRVV) {
                grOverlay.setisRendered(true);
                isGRVV = false;
            }
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
