package net.fragger.creatoroverlays.client.overlays;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fragger.creatoroverlays.client.OverlayHelper;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TPOverlay extends OverlayHelper implements HudRenderCallback {

    private static final Identifier TP_Overlay = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay.png");
    private static final Identifier TP_Overlay_White = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_white.png");
    private static final Identifier TP_Overlay_Red = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_red.png");
    private static final Identifier TP_Overlay_Blue = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_blue.png");
    private static final Identifier TP_Overlay_Selected = new Identifier(creatoroverlays.MOD_ID,"textures/overlays/tracking_point/tp_overlay_selected.png");

    MinecraftClient client;

    private boolean isRendered = false;
    private int x = 0;
    private int y = 0;
    private static int height = 0;
    private static int width = 0;
    private int tpColor = 0;
    private boolean isSelected = false;
    private boolean renderStatus = false;
    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        if(isRendered) {
            if (tpColor == 0) {
                render(matrixStack, TP_Overlay, x, y, width, height);
            } else if (tpColor == 1) {
                render(matrixStack, TP_Overlay_White, x, y, width, height);
            } else if (tpColor == 2) {
                render(matrixStack, TP_Overlay_Red, x, y, width, height);
            } else if (tpColor == 3) {
                render(matrixStack, TP_Overlay_Blue, x, y, width, height);
            }
            if (isSelected) {
                render(matrixStack, TP_Overlay_Selected, x, y, width, height);
            }
        }
    }

    //0 is black, 1 is white, 2 is red, 3 is blue
    public void updateColor() {
        if (tpColor == 0) {
            tpColor = 1;

        } else if (tpColor == 1) {
            tpColor = 2;
        } else if (tpColor == 2) {
            tpColor = 3;
        } else if (tpColor == 3) {
            tpColor = 0;
        }
        HudRenderCallback.EVENT.register(this);
    }
    public void reset() {
        client = MinecraftClient.getInstance();
        if (client != null) {
            height = client.getWindow().getScaledHeight() / 17;
            width = height;
            x = client.getWindow().getScaledWidth() / 2 - (width / 2) - 1;
            y = client.getWindow().getScaledHeight() / 2 - (height / 2) + 1;
        }
    }
    public void initializeTPOverlay() {
        this.reset();
    }
    public void moveUp() {
        if(isRendered & isSelected) {
            if (y > 0) {
                y--;
                HudRenderCallback.EVENT.register(this);
            }
        }
    }
    public void moveUpFast() {
        if(isRendered & isSelected) {
            if(y >= 5) {
                y = y - 5;
                HudRenderCallback.EVENT.register(this);
            }
        }
    }
    public void moveDown() {
        if (isRendered & isSelected) {
            if (y < client.getWindow().getScaledHeight() - height) {
                y++;
                HudRenderCallback.EVENT.register(this);
            }
        }
    }
    public void moveDownFast() {
        if (isRendered & isSelected) {
            if (y <= client.getWindow().getScaledHeight() - height - 5) {
                y = y+ 5;
                HudRenderCallback.EVENT.register(this);
            }
        }
    }
    public void moveRight() {
        if (isRendered & isSelected) {
            if (x < client.getWindow().getScaledWidth() - width) {
                x++;
                HudRenderCallback.EVENT.register(this);
            }
        }
    }
    public void moveRightFast() {
        if (isRendered & isSelected) {
            if (x <= client.getWindow().getScaledWidth() - width - 5) {
                x = x + 5;
                HudRenderCallback.EVENT.register(this);
            }
        }
    }
    public void moveLeft() {
        if (isRendered & isSelected) {
            if (x > 0) {
                x--;
                HudRenderCallback.EVENT.register(this);
            }
        }
    }
    public void moveLeftFast() {
        if(isRendered & isSelected) {
            if (x >= 5) {
                x = x - 5;
                HudRenderCallback.EVENT.register(this);
            }
        }
    }
    public void updateRenderStatus() {
            isRendered = !isRendered;
            renderStatus = !renderStatus;
            isSelected = true;
            HudRenderCallback.EVENT.register(this);
    }
    public void updateSelected() {
        isSelected = !isSelected;
    }
    public boolean isRendered() {
        return isRendered;
    }
    public void setIsRendered(boolean Rendered) {
        isRendered = Rendered;
        HudRenderCallback.EVENT.register(this);
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
        return tpColor;
    }
    public void setColor(int color) {
        tpColor = color;
    }

    public void setRenderStatus(boolean status) {
        renderStatus = status;
    }
}
