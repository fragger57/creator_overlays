package net.fragger.creatoroverlays;

import net.fabricmc.api.ClientModInitializer;
import net.fragger.creatoroverlays.event.KeyInputHandler;
import net.fragger.creatoroverlays.event.ClientListeners;

public class creatoroverlaysClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInputHandler keyInput = new KeyInputHandler();
        keyInput.register();
        keyInput.initialize();
        ClientListeners.listen();
    }
}
