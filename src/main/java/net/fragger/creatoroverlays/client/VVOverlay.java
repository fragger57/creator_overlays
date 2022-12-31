package net.fragger.creatoroverlays.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public class VVOverlay implements HudRenderCallback {
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

    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        if (isVVRendered == true) {
            //renders vv overlay normally
            if (isVVRO3Rendered == false & isVVGRRendered == false) {
                if (cocolor == 0) {
                    this.render(matrixStack, VV_Overlay);
                } else if (cocolor == 1) {
                    this.render(matrixStack, VV_Overlay_White);
                } else if (cocolor == 2){
                    this.render(matrixStack, VV_Overlay_Red);
                }
            }
            //renders vv overlay with ro3 overlay
            if (isVVRO3Rendered == true) {
                if (cocolor == 0) {
                    this.render(matrixStack, VV_OVERLAY_RO3);
                } else if (cocolor == 1) {
                    this.render(matrixStack, VV_Overlay_White_RO3);
                } else if (cocolor == 2) {
                    this.render(matrixStack, VV_Overlay_Red_RO3);
                }
            }
            //renders vv overlay with gr overlay
            if (isVVGRRendered == true) {
                if (cocolor == 0) {
                    if(coroation == 0) {
                        this.render(matrixStack, VV_OVERLAY_GR);
                    } else if (coroation == 90) {
                        this.render(matrixStack, VV_OVERLAY_GR_90);
                    } else if (coroation == 180) {
                        this.render(matrixStack, VV_OVERLAY_GR_180);
                    } else if (coroation == 270) {
                        this.render(matrixStack, VV_OVERLAY_GR_270);
                    }
                } else if (cocolor == 1) {
                    if(coroation == 0) {
                        this.render(matrixStack, VV_OVERLAY_White_GR);
                    } else if (coroation == 90) {
                        this.render(matrixStack, VV_OVERLAY_White_GR_90);
                    } else if (coroation == 180) {
                        this.render(matrixStack, VV_OVERLAY_White_GR_180);
                    } else if (coroation == 270) {
                        this.render(matrixStack, VV_OVERLAY_White_GR_270);
                    }
                } else if (cocolor == 2) {
                    if (coroation == 0) {
                        this.render(matrixStack, VV_OVERLAY_Red_GR);
                    } else if (coroation == 90) {
                        this.render(matrixStack, VV_OVERLAY_Red_GR_90);
                    } else if (coroation == 180) {
                        this.render(matrixStack, VV_OVERLAY_Red_GR_180);
                    } else if (coroation == 270) {
                        this.render(matrixStack, VV_OVERLAY_Red_GR_270);
                    }
                }
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
        int windowWidth = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            height = client.getWindow().getScaledHeight();
            width = height / 16 * 9 + 10;
            windowWidth = client.getWindow().getScaledWidth();
        }
        x = windowWidth / 2 - (width / 2);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        DrawableHelper.drawTexture(matrixStack, x, y, 0, 0, width, height, width, height);
    }
}
