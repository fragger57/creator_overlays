package net.fragger.creatoroverlays.util.config;

import com.mojang.datafixers.util.Pair;
import net.fragger.creatoroverlays.creatoroverlays;

public class COConfigs {
    public static SimpleConfig CONFIG;
    private static COConfigProvider configs;

    public static int OVERLAY_COLOR;

    public static void registerConfigs() {
        configs = new COConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(creatoroverlays.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("overlay.color", 0), "int value. 0 is black, 1 is white, 2 is red.");
    }

    private static void assignConfigs() {
        OVERLAY_COLOR = CONFIG.getOrDefault("overlay.color", 0);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
