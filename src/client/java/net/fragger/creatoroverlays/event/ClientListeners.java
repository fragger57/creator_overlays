package net.fragger.creatoroverlays.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fragger.creatoroverlays.util.config.COConfigs;

public class ClientListeners {

    public static void listen() {
        ClientPlayConnectionEvents.JOIN.register ((handler, sender, client) -> {
            if (COConfigs.DISPLAY_MODE == 1) {
                if (COConfigs.DISPLAY_RO3) {
                    KeyInputHandler.ro3GUI.toggle(COConfigs.DISPLAY_RO3);
                }
                KeyInputHandler.vvGUI.toggle(COConfigs.DISPLAY_VV);
                KeyInputHandler.camGUI.toggle(COConfigs.DISPLAY_CAM);
                KeyInputHandler.customGUI.toggle(COConfigs.DISPLAY_CUSTOM);
                KeyInputHandler.vvGUI.getOverlay().setRO3VV(COConfigs.DISPLAY_RO3VV);
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
