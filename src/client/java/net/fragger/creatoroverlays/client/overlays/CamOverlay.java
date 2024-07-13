package net.fragger.creatoroverlays.client.overlays;

import com.mojang.datafixers.util.Pair;
import net.fragger.creatoroverlays.client.Overlay;
import net.fragger.creatoroverlays.creatoroverlays;
import net.fragger.creatoroverlays.util.config.COConfigs;
import net.fragger.creatoroverlays.client.StaticOverlay;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.vvGUI;
import static net.fragger.creatoroverlays.event.KeyInputHandler.vvOverlay;

public class CamOverlay extends StaticOverlay implements Overlay {

    private static final Identifier FaceCamLeft = Identifier.of(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamleft.png");
    private static final Identifier FaceCamRight = Identifier.of(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamright.png");
    private static final Identifier FaceCamLeft_White = Identifier.of(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamleft_white.png");
    private static final Identifier FaceCamRight_White = Identifier.of(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamright_white.png");
    private static final Identifier FaceCamLeft_Red = Identifier.of(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamleft_red.png");
    private static final Identifier FaceCamRight_Red = Identifier.of(creatoroverlays.MOD_ID, "textures/overlays/face_cam/facecamright_red.png");

    private static boolean isRendered = false;

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickDelta) {
        if (isRendered) {
            render(drawContext, getTexture());
        }
    }

    public void updateRenderStatus() {
        isRendered = !isRendered;
        setisRendered(isRendered);
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
        COConfigs.updateConfig(new Pair<>("display.cam", isRendered));
        if (isRendered) {
            if (vvOverlay.isRendered()) {
                vvGUI.toggle();
            }
            if (COConfigs.DISPLAY_VV) {
                COConfigs.updateConfig(new Pair<>("display.vv", false));
            }
        }
        if (!COConfigs.DISPLAY_RO3 && !COConfigs.DISPLAY_VV && !COConfigs.DISPLAY_CAM && !COConfigs.DISPLAY_CUSTOM) {
            COConfigs.updateConfig(new Pair<>("display.ro3", true));
        }
    }
    public void setState(boolean s) {
        this.state = s;
    }
    public void setColor(int c) {
        this.color = c;
    }
}
