package net.fragger.creatoroverlays.event;

import com.mojang.datafixers.util.Pair;
import com.replaymod.core.KeyBindingRegistry;
import com.replaymod.core.Module;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.EventRegistrations;
import com.replaymod.render.events.ReplayRenderCallback;
import com.replaymod.replay.events.ReplayClosingCallback;
import com.replaymod.replay.events.ReplayOpenedCallback;
import net.fragger.creatoroverlays.util.config.COConfigs;

import static net.fragger.creatoroverlays.event.KeyInputHandler.*;
import static net.fragger.creatoroverlays.util.config.COConfigs.*;

public class ReplayListener extends EventRegistrations implements Module {

    public static boolean inReplay = false;
    public static boolean replayRendering = false;

    @Override
    public void initCommon() {
    }

    @Override
    public void initClient() {
        on(ReplayOpenedCallback.EVENT, replayHandler -> {
            inReplay = true;
            if (COConfigs.DISPLAY_MODE == 2) {
                if (DISPLAY_RO3) {
                    ro3GUI.toggle(DISPLAY_RO3);
                }
                vvGUI.toggle(DISPLAY_VV);
                camGUI.toggle(DISPLAY_CAM);
                customGUI.toggle(DISPLAY_CUSTOM);
                vvGUI.getOverlay().setRO3VV(DISPLAY_RO3VV);
            }
        });
        on(ReplayClosingCallback.EVENT, replayHandler -> {
            inReplay = false;
            if (COConfigs.DISPLAY_MODE == 2) {
                if (ro3GUI.getOverlay().isRendered()) {
                    ro3GUI.toggle();
                }
                if (vvGUI.getOverlay().isRendered()) {
                    vvGUI.toggle();
                }
                if (camGUI.getOverlay().isRendered()) {
                    camGUI.toggle();
                    COConfigs.updateConfig(new Pair<>("display.cam", true));
                }
                if (customGUI.getOverlay().isRendered()) {
                    customGUI.toggle();
                    COConfigs.updateConfig(new Pair<>("display.custom", true));
                }
            }
        });
        on(ReplayRenderCallback.Pre.EVENT, videoRenderer -> {
            replayRendering = true;
        });
        on(ReplayRenderCallback.Post.EVENT, videoRenderer -> {
            replayRendering = false;
        });
        register();
    }

    @Override
    public void registerKeyBindings(KeyBindingRegistry registry) {
    }
}
