package net.fragger.creatoroverlays.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fragger.creatoroverlays.client.GROverlay;
import net.fragger.creatoroverlays.client.RO3Overlay;
import net.fragger.creatoroverlays.client.VVOverlay;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static net.fragger.creatoroverlays.client.OverlayHelper.*;
import static net.fragger.creatoroverlays.client.VVOverlay.isGRVV;
import static net.fragger.creatoroverlays.client.VVOverlay.isRO3VV;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_CREATOROVERLAYS = "key.category.creatoroverlays.creatoroverlays";
    public static final String KEY_DISPLAY_RO3OVERLAY = "key.creatoroverlays.display_ro3overlay";
    public static final String KEY_DISPLAY_GROVERLAY = "key.creatoroverlays.display_groverlay";
    public static final String KEY_DISPLAY_VVOVERLAY = "key.creatoroverlays.display_vvoverlay";
    public static final String KEY_CO_COLOR = "key.creatoroverlays.co_color";
    public static final String KEY_CO_ROTATEUP = "key.creatoroverlays.co_rotateup";
    public static final String KEY_CO_ROTATEDOWN = "key.creatoroverlays.co_rotatedown";


    public static KeyBinding ro3displayKey;
    public static KeyBinding grdisplayKey;
    public static KeyBinding vvdisplayKey;
    public static KeyBinding cocolorKey;
    public static KeyBinding corotateupKey;
    public static KeyBinding corotatedownKey;

    public static RO3Overlay ro3Overlay = new RO3Overlay();
    public static GROverlay grOverlay = new GROverlay();
    public static VVOverlay vvOverlay = new VVOverlay();

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            //Handles RO3 Overlay Key Press
            if (ro3displayKey.wasPressed()) {
                if (!vvOverlay.isRendered()) {
                    ro3Overlay.updateRenderStatus();
                } else {
                    if (isGRVV) {
                        isGRVV = false;
                    }
                    isRO3VV = !isRO3VV;
                    HudRenderCallback.EVENT.register(vvOverlay);
                }
            }
            //Handles Golden Ratio Key Press
            if (grdisplayKey.wasPressed()) {
                if (!vvOverlay.isRendered()) {
                    grOverlay.updateRenderStatus();
                } else {
                    if (isRO3VV) {
                        isRO3VV = false;
                    }
                    isGRVV = !isGRVV;
                    HudRenderCallback.EVENT.register(vvOverlay);
                }
            }
            //Handles Vertical Video Key Press
            if (vvdisplayKey.wasPressed()) {
                vvOverlay.updateRenderStatus();
            }
            //Cycles Through Colors
            if (cocolorKey.wasPressed()) {
                colorCycle();
            }
            //Cycles Rotations Up
            if (corotateupKey.wasPressed()) {
                if (grOverlay.isRendered() || isGRVV) {
                    rotateUp();
                }
            }
            //Cycles Rotations Down
            if (corotatedownKey.wasPressed()) {
                if (grOverlay.isRendered() || isGRVV) {
                    rotateDown();
                    HudRenderCallback.EVENT.register(new GROverlay());
                    HudRenderCallback.EVENT.register(new VVOverlay());
                }
            }
        });
    }
    public static void register() {
        //sets the RO3 display key bind
        ro3displayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DISPLAY_RO3OVERLAY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F9,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets Golden Ratio key bind
        grdisplayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DISPLAY_GROVERLAY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F10,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets Vertial Video key bind
        vvdisplayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DISPLAY_VVOVERLAY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F8,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets color cycle key bind
        cocolorKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CO_COLOR,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F4,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets the rotate up key bind
        corotateupKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CO_ROTATEUP,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_BRACKET,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        corotatedownKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CO_ROTATEDOWN,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_BRACKET,
                KEY_CATEGORY_CREATOROVERLAYS
        ));

        registerKeyInputs();
    }
}
