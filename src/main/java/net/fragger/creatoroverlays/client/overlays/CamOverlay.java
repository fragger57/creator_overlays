package net.fragger.creatoroverlays.client.overlays;

import net.fragger.creatoroverlays.client.Overlay;
import net.fragger.creatoroverlays.client.StaticOverlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.vvGUI;
import static net.fragger.creatoroverlays.event.KeyInputHandler.vvOverlay;

public class CamOverlay extends StaticOverlay implements Overlay {

    private static final Identifier FaceCamLeft = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamleft.png");
    private static final Identifier FaceCamRight = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamright.png");
    private static final Identifier FaceCamLeft_White = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamleft_white.png");
    private static final Identifier FaceCamRight_White = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamright_white.png");
    private static final Identifier FaceCamLeft_Red = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamleft_red.png");
    private static final Identifier FaceCamRight_Red = new Identifier(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamright_red.png");

    private static boolean isRendered = false;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if (isRendered) {
            render(drawContext, getTexture());
        }
    }

    public void updateRenderStatus() {
        if (!isRendered) {
            if (vvOverlay.isRendered()) {
                vvGUI.toggle();
            }
            isRendered = true;
        } else {
            isRendered = false;
        }
    }

    public Identifier getTexture() {
        Identifier texture = FaceCamLeft;
        if (state) {
            if (color == 1) {
                texture = FaceCamLeft_White;
            } else if (color == 2) {
                texture = FaceCamLeft_Red;
            } else {
                texture = FaceCamLeft;
            }
        } else {
            if (color == 1) {
                texture = FaceCamRight_White;
            } else if (color == 2) {
                texture = FaceCamRight_Red;
            } else {
                texture = FaceCamRight;
            }
        }
        return texture;
    }

    public boolean isRendered() {
        return isRendered;
    }

    public void setisRendered(boolean rendered) {
        isRendered = rendered;
    }
}
