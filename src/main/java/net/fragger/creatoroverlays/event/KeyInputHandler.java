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

    public static boolean isRO3Rendered = false;
    public static boolean isVVRO3Rendered = false;
    public static boolean isGRRendered = false;
    public static boolean isVVGRRendered = false;
    public static boolean isVVRendered = false;
    private static boolean isRendered = false;
    public static int cocolor = 0;
    public static int coroation = 0;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            //toggles RO3 overlay on and off
            if (ro3displayKey.wasPressed()) {
                if (isVVRendered == false) {
                    if (isGRRendered == true) {
                        isGRRendered = false;
                        isRendered = false;
                        HudRenderCallback.EVENT.register(new GROverlay());
                    }
                    if (isRO3Rendered == false) {
                        isRO3Rendered = true;
                        isRendered = true;
                    } else {
                        isRO3Rendered = false;
                        isRendered = false;
                    }
                    HudRenderCallback.EVENT.register(new RO3Overlay());
                } else {
                    if (isVVGRRendered == true) {
                        isVVGRRendered = false;
                    }
                    if (isVVRO3Rendered == false) {
                        isVVRO3Rendered = true;
                    } else {
                        isVVRO3Rendered = false;
                    }
                     HudRenderCallback.EVENT.register(new VVOverlay());
                }
            }
                //toggles Golden Ratio overlay on and off
            if (grdisplayKey.wasPressed()) {
                if (isVVRendered == false) {
                    if (isRO3Rendered == true) {
                        isRO3Rendered = false;
                        isRendered = false;
                        HudRenderCallback.EVENT.register(new RO3Overlay());
                    }
                    if (isGRRendered == false) {
                        isGRRendered = true;
                        isRendered = true;
                    } else {
                        isGRRendered = false;
                        isRendered = false;
                    }
                    HudRenderCallback.EVENT.register(new GROverlay());
                } else {
                    if (isVVRO3Rendered == true) {
                        isVVRO3Rendered = false;
                    }
                    if (isVVGRRendered == false) {
                        isVVGRRendered = true;
                    } else {
                        isVVGRRendered = false;
                    }
                    HudRenderCallback.EVENT.register(new VVOverlay());
                }
            }
            //toggles Vertical Video overlay on and off
            if (vvdisplayKey.wasPressed()) {
                // when vv overlay is turned on
                if (isRO3Rendered == true) {
                    isRO3Rendered = false;
                    isVVRO3Rendered = true;
                    HudRenderCallback.EVENT.register(new RO3Overlay());
                }
                //when vv overlay is turned off
                if (isVVRendered == true & isVVRO3Rendered == true) {
                    isRO3Rendered = true;
                    isVVRO3Rendered = false;
                    HudRenderCallback.EVENT.register(new RO3Overlay());
                }
                //when vv overlay is turned on
                if (isGRRendered == true) {
                    isGRRendered = false;
                    isVVGRRendered = true;
                    HudRenderCallback.EVENT.register(new GROverlay());
                }
                //when vv overlay is turned off
                if (isVVRendered == true & isVVGRRendered == true) {
                    isGRRendered = true;
                    isVVGRRendered = false;
                    HudRenderCallback.EVENT.register(new GROverlay());
                }
                if (isVVRendered == false) {
                    isVVRendered = true;
                    isRendered = true;
                } else {
                    isVVRendered = false;
                    isRendered = false;
                }
                HudRenderCallback.EVENT.register(new VVOverlay());
            }
            //cycles through colors
            if(cocolorKey.wasPressed()) {
                //cycles through colors of RO3 overlay
                if (isRO3Rendered == true) {
                    colorCycle();
                    HudRenderCallback.EVENT.register(new RO3Overlay());
                }
                //cycles through colors of GR overlay
                if (isGRRendered == true) {
                    colorCycle();
                    HudRenderCallback.EVENT.register(new GROverlay());
                }
                // cycles through colors of VV overlay
                if (isVVRendered == true) {
                    colorCycle();
                    HudRenderCallback.EVENT.register(new VVOverlay());
                }
            }
            //cycles up through rotations
            if (corotateupKey.wasPressed()) {
                if (isGRRendered == true) {
                    rotationCycleUp();
                    HudRenderCallback.EVENT.register(new GROverlay());
                }
                if (isVVGRRendered == true) {
                    rotationCycleUp();
                    HudRenderCallback.EVENT.register(new VVOverlay());
                }
            }
            //cycles down through rotations
            if (corotatedownKey.wasPressed()) {
                if (isGRRendered == true) {
                    rotationCycleDown();
                    HudRenderCallback.EVENT.register(new GROverlay());
                }
                if (isVVGRRendered == true) {
                    rotationCycleDown();
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
    private static void colorCycle(){
        //0 is black, 1 is white, and 2 is red
        if (cocolor == 0) {
            cocolor = 1;
        } else if (cocolor == 1) {
            cocolor = 2;
        } else {
            cocolor = 0;
        }
    }
    private static void rotationCycleUp(){
        //cycles rotation up
        if (coroation == 0) {
            coroation = 90;
        } else if (coroation == 90) {
            coroation = 180;
        } else if (coroation == 180) {
            coroation = 270;
        } else {
            coroation = 0;
        }
    }
    private static void rotationCycleDown(){
        //cycles rotation down
        if (coroation == 180) {
            coroation = 90;
        } else if (coroation == 270) {
            coroation = 180;
        } else if (coroation == 0) {
            coroation = 270;
        } else {
            coroation = 0;
        }
    }
}
