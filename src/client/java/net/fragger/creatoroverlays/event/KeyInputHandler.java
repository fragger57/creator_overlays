package net.fragger.creatoroverlays.event;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fragger.creatoroverlays.client.TPHandler;
import net.fragger.creatoroverlays.client.gui.COScreen;
import net.fragger.creatoroverlays.client.gui.RootGUI;
import net.fragger.creatoroverlays.client.gui.overlayguis.*;
import net.fragger.creatoroverlays.client.overlays.*;
import net.fragger.creatoroverlays.util.config.COConfigs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler implements HudRenderCallback {


    public static final String KEY_CATEGORY_CREATOROVERLAYS = "key.category.creatoroverlays.creatoroverlays";
    public static final String KEY_DISPLAY_RO3OVERLAY = "key.creatoroverlays.display_ro3overlay";
    public static final String KEY_DISPLAY_VVOVERLAY = "key.creatoroverlays.display_vvoverlay";
    public static final String KEY_DISPLAY_TPOVERLAY = "key.creatoroverlays.display_tpoverlay";
    public static final String KEY_DISPLAY_CAMOVERLAY = "key.creatoroverlays.display_camoverlay";
    public static final String KEY_DISPLAY_CUSTOMOVERLAY = "key.creatoroverlays.display_custom";
    public static final String KEY_CO_COLOR = "key.creatoroverlays.co_color";
    public static final String KEY_CO_ROTATEUP = "key.creatoroverlays.co_rotateup";
    public static final String KEY_CO_ROTATEDOWN = "key.creatoroverlays.co_rotatedown";
    public static final String KEY_CO_UP = "key.creatoroverlays.co_up";
    public static final String KEY_CO_DOWN = "key.creatoroverlays.co_down";
    public static final String KEY_CO_RIGHT = "key.creatoroverlays.co_right";
    public static final String KEY_CO_LEFT = "key.creatoroverlays.co_left";
    public static final String KEY_MODIFIER = "key.creatoroverlays.co_modifier";
    public static final String KEY_GUI = "key.creatoroverlays.gui";
    public static final String KEY_SELECT_TP_COLOR = "key.creatoroverlays.select.tp_color";

    public static KeyBinding ro3displayKey;
    public static KeyBinding vvdisplayKey;
    public static KeyBinding tpdisplayKey;
    public static KeyBinding camdisplayKey;
    public static KeyBinding customDisplayKey;
    public static KeyBinding cocolorKey;
    public static KeyBinding corotateupKey;
    public static KeyBinding corotatedownKey;
    public static KeyBinding coupKey;
    public static KeyBinding codownKey;
    public static KeyBinding corightKey;
    public static KeyBinding coleftKey;
    public static KeyBinding modifierKey;
    public static KeyBinding guiKey;
    public static KeyBinding selectAllTPColor;

    public static RO3GUI ro3GUI = new RO3GUI();
    public static RO3Overlay ro3Overlay = ro3GUI.getOverlay();
    public static VVGUI vvGUI = new VVGUI();
    public static VVOverlay vvOverlay = vvGUI.getOverlay();
    public static CAMGUI camGUI = new CAMGUI();
    public static CamOverlay camOverlay = camGUI.getOverlay();
    public static TPHandler tpOverlay = new TPHandler();
    public static TPGUI tpGUI = new TPGUI();
    public static CustomGUI customGUI = new CustomGUI();
    public static CustomOverlay customOverlay = customGUI.getOverlay();
    public static RootGUI coGUI = new RootGUI();
    public static COScreen coScreen = new COScreen(coGUI);

    private static int num = 0;


    //uses Hud Render Callback to check for keypresses, as to fix the issue with not being able to toggle overlays while Replay Mod recordings are paused
    //very unconventional, but it works lol
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        /*
         *
         * Toggle Keys
         */
        //Handles RO3 Overlay Key Press
        if (ro3displayKey.wasPressed()) {
            if (vvOverlay.isRendered()) {
                ro3Overlay.setisRendered(false);
                vvOverlay.setRO3VV(!vvOverlay.getRO3VV());
            } else {
                ro3GUI.toggle();
            }
        }
        //Handles Vertical Video Key Press
        if (vvdisplayKey.wasPressed()) {
            vvGUI.toggle();
        }
        //Handels Tracking Points Key Press
        if (tpdisplayKey.wasPressed()) {
            tpOverlay.updateRenderStatus();
        }
        //Handles Cam Key Press
        if (camdisplayKey.wasPressed()) {
            camGUI.toggle();
        }
        //Handles Custom Key Press
        if (customDisplayKey.wasPressed()) {
            customGUI.toggle();
        }

        /*
         *
         * Overlay Cycle Keys
         */
        //Cycles Through Colors
        if (cocolorKey.wasPressed()) {
            if (tpOverlay.isRendered()) {
                tpOverlay.cycleColor();
            } else if (camOverlay.isRendered()) {
                camGUI.changeColor();
            } else if (ro3Overlay.isRendered()) {
                ro3GUI.changeColor();
            } else if (vvOverlay.isRendered()){
                vvGUI.changeColor();
            }
        }
        //Cycles Rotations Up
        if (corotateupKey.wasPressed()) {
            if (tpOverlay.isRendered()) {
                tpOverlay.cycleSelectedUp();
            } else if (camOverlay.isRendered()) {
                camGUI.swapSides();
            } else if (ro3Overlay.isRendered()) {
                ro3GUI.changeGrid();
            } else if (customOverlay.isRendered()) {
                customGUI.changeOverlayUp();
            }
        }
        //Cycles Rotations Down
        if (corotatedownKey.wasPressed()) {
            if (tpOverlay.isRendered()) {
                tpOverlay.cycleSelectedDown();
            } else if (camOverlay.isRendered()) {
                camGUI.swapSides();
            } else if (ro3Overlay.isRendered()) {
                ro3GUI.changeGrid();
            } else if (customOverlay.isRendered()) {
                customGUI.changeOverlayDown();
            }
        }

        /*
         *
         * Tracking Point Movement Keys
         */
        //moves tp overlay up
        if (num == 0) {
            if (coupKey.isPressed()) {
                if (modifierKey.isPressed()) {
                    tpOverlay.moveUp();
                } else {
                    tpOverlay.moveUpFast();
                }
            }
            //moves tp overlay down
            if (codownKey.isPressed()) {
                if (modifierKey.isPressed()) {
                    tpOverlay.moveDown();
                } else {
                    tpOverlay.moveDownFast();
                }
            }
            //moves tp overlay right
            if (corightKey.isPressed()) {
                if (modifierKey.isPressed()) {
                    tpOverlay.moveRight();
                } else {
                    tpOverlay.moveRightFast();
                }
            }
            //moves tp overlay left
            if (coleftKey.isPressed()) {
                if (modifierKey.isPressed()) {
                    tpOverlay.moveLeft();
                } else {
                    tpOverlay.moveLeftFast();
                }
            }
            num = 1;
        } else {
            num = 0;
        }

        //opens GUI
        if (guiKey.wasPressed()) {
            MinecraftClient.getInstance().setScreen(coScreen);
            COConfigs.writeConfigs();
        }
        //selects all tracking points of the same color
        if (selectAllTPColor.wasPressed()) {
           tpOverlay.selectSameColor(tpOverlay.getSelected().color());
        }
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
                InputUtil.UNKNOWN_KEY.getCode(),
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets Cam key bind
        camdisplayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DISPLAY_CAMOVERLAY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F7,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        //sets Custom key bind
        customDisplayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DISPLAY_CUSTOMOVERLAY,
                InputUtil.Type.KEYSYM,
                InputUtil.UNKNOWN_KEY.getCode(),
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
        coupKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CO_UP,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UP,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        codownKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CO_DOWN,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_DOWN,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        corightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CO_RIGHT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        coleftKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CO_LEFT,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT,
                KEY_CATEGORY_CREATOROVERLAYS
        ));
        guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
           KEY_GUI,
           InputUtil.Type.KEYSYM,
           GLFW.GLFW_KEY_GRAVE_ACCENT,
           KEY_CATEGORY_CREATOROVERLAYS
        ));
        selectAllTPColor = KeyBindingHelper.registerKeyBinding(new KeyBinding(
           KEY_SELECT_TP_COLOR,
           InputUtil.Type.KEYSYM,
           InputUtil.UNKNOWN_KEY.getCode(),
           KEY_CATEGORY_CREATOROVERLAYS
        ));
    }
    public void initialize(){
        HudRenderCallback.EVENT.register(ro3Overlay);
        HudRenderCallback.EVENT.register(vvOverlay);
        HudRenderCallback.EVENT.register(camOverlay);
        HudRenderCallback.EVENT.register(customOverlay);
        HudRenderCallback.EVENT.register(this);
    }
}
