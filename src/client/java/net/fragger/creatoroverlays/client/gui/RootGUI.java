package net.fragger.creatoroverlays.client.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WTabPanel;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.event.KeyInputHandler.*;

public class RootGUI extends LightweightGuiDescription {
    private static final Identifier LOGO = new Identifier(creatoroverlays.MOD_ID,"textures/icons/logo.png");
    private static final Identifier RO3 = new Identifier(creatoroverlays.MOD_ID,"textures/icons/gridline_tab.png");
    private static final Identifier VV = new Identifier(creatoroverlays.MOD_ID,"textures/icons/vv_tab.png");
    private static final Identifier CAM = new Identifier(creatoroverlays.MOD_ID,"textures/icons/cam_tab.png");
    private static final Identifier TP = new Identifier(creatoroverlays.MOD_ID,"textures/icons/tp_tab.png");
    private static final Identifier CUSTOM = new Identifier(creatoroverlays.MOD_ID,"textures/icons/custom_tab.png");

    public static final Identifier TOGGLE_ON = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/toggle_on.png");
    public static final Identifier TOGGLE_OFF = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/toggle_off.png");
    public static final Identifier SELECT_ON = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/select_on.png");
    public static final Identifier SELECT_OFF = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/select_off.png");
    public static final Identifier RESET = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/reset_arrow.png");

    public static int guiWidth = 250;
    public static int guiHeight = 230;

    public WTabPanel root = new WTabPanel();
    public COGUI coGUI = new COGUI();

    public RootGUI() {

        setRootPanel(root);

        root.add(coGUI.coPanel, builder -> {
            builder.icon(new TextureIcon(LOGO));
            builder.tooltip(Text.literal("Creator Overlays"));
        });

        root.add(ro3GUI.ro3Panel, builder -> {
            builder.icon(new TextureIcon(RO3));
            builder.tooltip(Text.literal("Gridlines"));
        });

        root.add(vvGUI.vvPanel, builder -> {
            builder.icon(new TextureIcon(VV));
            builder.tooltip(Text.literal("Vertical Video"));
        });

        root.add(camGUI.camPanel, builder -> {
            builder.icon(new TextureIcon(CAM));
            builder.tooltip(Text.literal("Face Cam"));
        });

        root.add(tpGUI.tpPanel, builder -> {
            builder.icon(new TextureIcon(TP));
            builder.tooltip(Text.literal("Tracking Points"));
        });

        root.add(customGUI.customPanel, builder -> {
            builder.icon(new TextureIcon(CUSTOM));
            builder.tooltip(Text.literal("Custom Overlay"));
        });
    }
}
