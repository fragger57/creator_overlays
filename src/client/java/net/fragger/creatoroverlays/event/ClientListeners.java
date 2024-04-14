package net.fragger.creatoroverlays.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fragger.creatoroverlays.util.config.COConfigs;

import static net.fragger.creatoroverlays.event.KeyInputHandler.*;
import static net.fragger.creatoroverlays.event.KeyInputHandler.customGUI;
import static net.fragger.creatoroverlays.util.config.COConfigs.*;
import static net.fragger.creatoroverlays.util.config.COConfigs.DISPLAY_CUSTOM;

public class ClientListeners {

    public static void listen() {
        ClientPlayConnectionEvents.JOIN.register ((handler, sender, client) -> {
            if (COConfigs.DISPLAY_MODE == 1) {
                if (DISPLAY_RO3) {
                    ro3GUI.toggle(DISPLAY_RO3);
                }
                vvGUI.toggle(DISPLAY_VV);
                camGUI.toggle(DISPLAY_CAM);
                customGUI.toggle(DISPLAY_CUSTOM);
                vvGUI.getOverlay().setRO3VV(DISPLAY_RO3VV);
            }
        });

        ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
            COConfigs.writeConfigs();
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            COConfigs.writeConfigs();
        });
    }
}
