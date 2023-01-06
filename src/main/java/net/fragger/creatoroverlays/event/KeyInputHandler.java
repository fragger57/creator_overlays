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

import static net.fragger.creatoroverlays.client.RenderHandler.*;
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

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            //Handles RO3 Overlay Key Press
            if (ro3displayKey.wasPressed()) {
                if (isGRRendered){
                    updateGRStatus();
                    HudRenderCallback.EVENT.register(new GROverlay());
                }
                if(isVVRendered){
                    updateRO3Status();
                    HudRenderCallback.EVENT.register(new VVOverlay());
                } else {
                    updateRO3Status();
                    HudRenderCallback.EVENT.register(new RO3Overlay());
                }
            }
            //Handles Golden Ratio Key Press
            if (grdisplayKey.wasPressed()) {
                if (isRO3Rendered){
                    updateRO3Status();
                    HudRenderCallback.EVENT.register(new RO3Overlay());
                }
                if (isVVRendered) {
                    updateGRStatus();
                    HudRenderCallback.EVENT.register(new GROverlay());
                } else {
                    updateGRStatus();
                    HudRenderCallback.EVENT.register(new GROverlay());
                }
            }
            //Handles Vertical Video Key Press
            if (vvdisplayKey.wasPressed()) {
                if (isVVRendered) {
                    updateVVStatus();
                    HudRenderCallback.EVENT.register(new VVOverlay());
                    if (isRO3Rendered) {
                        HudRenderCallback.EVENT.register(new RO3Overlay());
                    }
                    if (isGRRendered) {
                        HudRenderCallback.EVENT.register(new GROverlay());
                    }
                } else {
                    updateVVStatus();
                    HudRenderCallback.EVENT.register(new RO3Overlay());
                    HudRenderCallback.EVENT.register(new GROverlay());
                    HudRenderCallback.EVENT.register(new VVOverlay());
                }
            }
            //Cycles Through Colors
            if (cocolorKey.wasPressed()) {
                if (!isVVRendered) {
                    //cycles through colors of RO3 overlay
                    if (isRO3Rendered) {
                        colorCycle();
                        HudRenderCallback.EVENT.register(new RO3Overlay());
                    }
                    //cycles through colors of GR overlay
                    if (isGRRendered) {
                        colorCycle();
                        HudRenderCallback.EVENT.register(new RO3Overlay());
                    }
                }
                //cycles through colors of VV overlay
                if (isVVRendered) {
                    colorCycle();
                    HudRenderCallback.EVENT.register(new VVOverlay());
                }
            }
            //Cycles Rotations Up
            if (corotateupKey.wasPressed()) {
                if (isGRRendered || isGRVV) {
                    rotateUp();
                }
            }
            //Cycles Rotations Down
            if (corotatedownKey.wasPressed()) {
                if (isGRRendered || isGRVV) {
                    rotateDown();
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
