package net.fragger.creatoroverlays;

import net.fabricmc.api.ClientModInitializer;
import net.fragger.creatoroverlays.event.KeyInputHandler;

public class creatoroverlaysClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //HudRenderCallback.EVENT.register(new RO3Overlay());
        KeyInputHandler.register();
    }
}
