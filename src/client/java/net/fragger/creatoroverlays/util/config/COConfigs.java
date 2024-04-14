package net.fragger.creatoroverlays.util.config;

import com.mojang.datafixers.util.Pair;
import net.fragger.creatoroverlays.creatoroverlays;

public class COConfigs {
    public static SimpleConfig CONFIG;
    private static COConfigProvider configs;

    public static int OVERLAY_COLOR, COLOR_MODE, DISPLAY_MODE, CUSTOM_MODE, RO3_COLOR, VV_COLOR, CAM_COLOR;

    public static boolean DISPLAY_RO3, DISPLAY_VV, DISPLAY_CAM, DISPLAY_CUSTOM, DISPLAY_RO3VV, RO3_MODE, CAM_MODE;

    public static void registerConfigs() {
        configs = new COConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(creatoroverlays.MOD_ID).provider(configs).request();
        assignConfigs();
        reCreateConfigs();
    }

    public static void updateConfig(Pair<String, ?> keyValuePair) {
        configs.updateKeyValuePair(keyValuePair);
    }
    public static void writeConfigs() {
        CONFIG.updateConfig();
        CONFIG = SimpleConfig.of(creatoroverlays.MOD_ID).provider(configs).request();
        assignConfigs();
    }

    public static Object getValue(String key) {
        return configs.getValue(key);
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("overlay.color", 0));
        configs.addKeyValuePair(new Pair<>("color.mode", 0));
        configs.addKeyValuePair(new Pair<>("display.mode", 0));
        configs.addKeyValuePair(new Pair<>("display.ro3", true));
        configs.addKeyValuePair(new Pair<>("display.vv", false));
        configs.addKeyValuePair(new Pair<>("display.cam", false));
        configs.addKeyValuePair(new Pair<>("display.custom", false));
        configs.addKeyValuePair(new Pair<>("ro3.mode", true));
        configs.addKeyValuePair(new Pair<>("display.ro3vv", false));
        configs.addKeyValuePair(new Pair<>("cam.mode", true));
        configs.addKeyValuePair(new Pair<>("custom.mode", 0));
        configs.addKeyValuePair(new Pair<>("ro3.color", 0));
        configs.addKeyValuePair(new Pair<>("vv.color", 0));
        configs.addKeyValuePair(new Pair<>("cam.color", 0));
    }

    private static void reCreateConfigs() {
        configs.removeKeyValuePairs();
        configs.addKeyValuePair(new Pair<>("overlay.color", OVERLAY_COLOR));
        configs.addKeyValuePair(new Pair<>("color.mode", COLOR_MODE));
        configs.addKeyValuePair(new Pair<>("display.mode", DISPLAY_MODE));
        configs.addKeyValuePair(new Pair<>("display.ro3", DISPLAY_RO3));
        configs.addKeyValuePair(new Pair<>("display.vv", DISPLAY_VV));
        configs.addKeyValuePair(new Pair<>("display.cam", DISPLAY_CAM));
        configs.addKeyValuePair(new Pair<>("display.custom", DISPLAY_CUSTOM));
        configs.addKeyValuePair(new Pair<>("ro3.mode", RO3_MODE));
        configs.addKeyValuePair(new Pair<>("display.ro3vv", DISPLAY_RO3VV));
        configs.addKeyValuePair(new Pair<>("cam.mode", CAM_MODE));
        configs.addKeyValuePair(new Pair<>("custom.mode", CUSTOM_MODE));
        configs.addKeyValuePair(new Pair<>("ro3.color", RO3_COLOR));
        configs.addKeyValuePair(new Pair<>("vv.color", VV_COLOR));
        configs.addKeyValuePair(new Pair<>("cam.color", CAM_COLOR));
    }

    private static void assignConfigs() {
        OVERLAY_COLOR = CONFIG.getOrDefault("overlay.color", 0);
        COLOR_MODE = CONFIG.getOrDefault("color.mode", 0);
        DISPLAY_MODE = CONFIG.getOrDefault("display.mode", 0);
        DISPLAY_RO3 = CONFIG.getOrDefault("display.ro3", true);
        DISPLAY_VV = CONFIG.getOrDefault("display.vv", false);
        DISPLAY_CAM = CONFIG.getOrDefault("display.cam", false);
        DISPLAY_CUSTOM = CONFIG.getOrDefault("display.custom", false);
        RO3_MODE = CONFIG.getOrDefault("ro3.mode", true);
        DISPLAY_RO3VV = CONFIG.getOrDefault("display.ro3vv", false);
        CAM_MODE = CONFIG.getOrDefault("cam.mode", true);
        CUSTOM_MODE = CONFIG.getOrDefault("custom.mode", 0);
        RO3_COLOR = CONFIG.getOrDefault("ro3.color", 0);
        VV_COLOR = CONFIG.getOrDefault("vv.color", 0);
        CAM_COLOR = CONFIG.getOrDefault("cam.color", 0);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
