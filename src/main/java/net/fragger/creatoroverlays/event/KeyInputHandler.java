package net.fragger.creatoroverlays.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fragger.creatoroverlays.client.TPHandler;
import net.fragger.creatoroverlays.client.GUI.TrackingPointsGUI;
import net.fragger.creatoroverlays.client.GUI.TrackingPointsScreen;
import net.fragger.creatoroverlays.client.overlays.GROverlay;
import net.fragger.creatoroverlays.client.overlays.RO3Overlay;
import net.fragger.creatoroverlays.client.overlays.VVOverlay;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static net.fragger.creatoroverlays.client.OverlayHelper.*;
import static net.fragger.creatoroverlays.client.overlays.VVOverlay.isGRVV;
import static net.fragger.creatoroverlays.client.overlays.VVOverlay.isRO3VV;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_CREATOROVERLAYS = "key.category.creatoroverlays.creatoroverlays";
    public static final String KEY_DISPLAY_RO3OVERLAY = "key.creatoroverlays.display_ro3overlay";
    public static final String KEY_DISPLAY_GROVERLAY = "key.creatoroverlays.display_groverlay";
    public static final String KEY_DISPLAY_VVOVERLAY = "key.creatoroverlays.display_vvoverlay";
    public static final String KEY_DISPLAY_TPOVERLAY = "key.creatoroverlays.display_tpoverlay";
    public static final String KEY_CO_COLOR = "key.creatoroverlays.co_color";
    public static final String KEY_CO_ROTATEUP = "key.creatoroverlays.co_rotateup";
    public static final String KEY_CO_ROTATEDOWN = "key.creatoroverlays.co_rotatedown";
    public static final String KEY_TP_UP = "key.creatoroverlays.tp_up";
    public static final String KEY_TP_DOWN = "key.creatoroverlays.tp_down";
    public static final String KEY_TP_RIGHT = "key.creatoroverlays.tp_right";
    public static final String KEY_TP_LEFT = "key.creatoroverlays.tp_left";
    public static final String KEY_MODIFIER = "key.creatoroverlays.co_modifier";

    public static KeyBinding ro3displayKey;
    public static KeyBinding grdisplayKey;
    public static KeyBinding vvdisplayKey;
    public static KeyBinding tpdisplayKey;
    public static KeyBinding cocolorKey;
    public static KeyBinding corotateupKey;
    public static KeyBinding corotatedownKey;
    public static KeyBinding tpupKey;
    public static KeyBinding tpdownKey;
    public static KeyBinding tprightKey;
    public static KeyBinding tpleftKey;
    public static KeyBinding modifierKey;


    public static RO3Overlay ro3Overlay = new RO3Overlay();
    public static GROverlay grOverlay = new GROverlay();
    public static VVOverlay vvOverlay = new VVOverlay();
    public static TPHandler tpOverlay = new TPHandler();
    public static TrackingPointsGUI tpGUI = new TrackingPointsGUI();
    public static TrackingPointsScreen tpScreen = new TrackingPointsScreen(tpGUI);

    private static int num = 0;

    public static void registerKeyInputs() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            /*
             *
             * Toggle Keys
             */
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
            //Handels Tracking Points Key Press
            if (tpdisplayKey.wasPressed()) {
                if (num == 0) {
                    num++;
                    tpOverlay.initializeOverlay();
                }
                if (modifierKey.isPressed()) {
                    MinecraftClient.getInstance().setScreen(tpScreen);
                } else {
                    tpOverlay.updateRenderStatus();
                }
            }

            /*
             *
             * Overlay Cycle Keys
             */
            //Cycles Through Colors
            if (cocolorKey.wasPressed()) {
                if (ro3Overlay.isRendered() || grOverlay.isRendered() || vvOverlay.isRendered()) {
                    colorCycle();
                }
                if (tpOverlay.isRendered()) {
                    tpOverlay.cycleColor();
                }
            }
            //Cycles Rotations Up
            if (corotateupKey.wasPressed()) {
                if (tpOverlay.isRendered()) {
                    tpOverlay.cycleSelectedUp();
                } else if (grOverlay.isRendered() || isGRVV) {
                    rotateUp();
                }
            }
            //Cycles Rotations Down
            if (corotatedownKey.wasPressed()) {
                if (tpOverlay.isRendered()) {
                    tpOverlay.cycleSelectedDown();
                } else if (grOverlay.isRendered() || isGRVV) {
                    rotateDown();
                }
            }

            /*
             *
             * Tracking Point Movement Keys
             */
            //moves tp overlay up
            if (tpupKey.isPressed()) {
                if (modifierKey.isPressed()) {
                    tpOverlay.moveUp();
                } else {
                    tpOverlay.moveUpFast();
                }
            }
            //moves tp overlay down
            if (tpdownKey.isPressed()) {
                if (modifierKey.isPressed()) {
                    tpOverlay.moveDown();
                } else {
                    tpOverlay.moveDownFast();
                }
            }
            //moves tp overlay right
            if (tprightKey.isPressed()) {
                if (modifierKey.isPressed()) {
                    tpOverlay.moveRight();
                } else {
                    tpOverlay.moveRightFast();
                }
            }
            //moves tp overlay left
            if (tpleftKey.isPressed()) {
                if (modifierKey.isPressed()) {
                    tpOverlay.moveLeft();
                } else {
                    tpOverlay.moveLeftFast();
                }
            }
        });
    }
    public static void register() {
        //set modifier key bind
        modifierKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_MODIFIER,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_CONTROL,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
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
        //set Tracking Point key bind
        tpdisplayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DISPLAY_TPOVERLAY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F7,
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
        tpupKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TP_UP,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UP,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        tpdownKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TP_DOWN,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_DOWN,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        tprightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TP_RIGHT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        tpleftKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TP_LEFT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT,
                KEY_CATEGORY_CREATOROVERLAYS
        ));

        registerKeyInputs();
    }
}
