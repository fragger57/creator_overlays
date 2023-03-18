package net.fragger.creatoroverlays.client.GUI;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.client.TPHandler.*;
import static net.fragger.creatoroverlays.event.KeyInputHandler.tpOverlay;
import static net.fragger.creatoroverlays.event.KeyInputHandler.tpScreen;

public class TrackingPointsGUI extends LightweightGuiDescription {

    private static final Identifier TOGGLE_ON = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/toggle_on.png");
    private static final Identifier TOGGLE_OFF = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/toggle_off.png");

    WPlainPanel root = new WPlainPanel();

    public static WToggleButton toggleAll = new WToggleButton(TOGGLE_ON, TOGGLE_OFF);

    public static TPGuiSection row1;
    public static TPGuiSection row2;
    public static TPGuiSection row3;
    public static TPGuiSection row4;

    public TrackingPointsGUI() {
        tp1.setColor(0);
        tp2.setColor(1);
        tp3.setColor(2);
        tp4.setColor(3);

        setRootPanel(root);
        root.setSize(180, 230);

        //top row
        WLabel label = new WLabel(Text.of("Tracking Points"));
        root.add(label, 80, 10);
        label.setHorizontalAlignment(HorizontalAlignment.CENTER);

        //Tracking Point 1 Row
        row1 = new TPGuiSection(Text.of("Tracking Point 1"), 2, tp1, root);

        //Tracking Point 2 Row
        row2 = new TPGuiSection(Text.of("Tracking Point 2"), 4, tp2, root);

        //Tracking Point 3 Row
        row3 = new TPGuiSection(Text.of("Tracking Point 3"), 6, tp3, root);

        //Tracking Point 4 Row
        row4 = new TPGuiSection(Text.of("Tracking Point 4"), 8, tp4, root);

        //Bottom Row
        WLabel toggleLabel = new WLabel(Text.of("Toggle All"));
        root.add(toggleLabel, 10, 190);

        root.add(toggleAll, 10, 200, 20, 20);
        toggleAll.setOnToggle(on -> {
            tpOverlay.updateRenderStatus();
        });

        WButton close = new WButton(Text.of("Close"));
        root.add(close, 130, 200, 40, 20);
        close.setOnClick(() -> {
            tpScreen.close();
        });

    }
    public void updateSelected(TPGuiSection row) {
        if (row == row1) {
            row1.selectOn();

            row2.selectOff();
            row3.selectOff();
            row4.selectOff();
        } else if (row == row2) {
            row2.selectOn();

            row1.selectOff();
            row3.selectOff();
            row4.selectOff();
        } else if (row == row3) {
            row3.selectOn();

            row1.selectOff();
            row2.selectOff();
            row4.selectOff();
        } else if (row == row4) {
            row4.selectOn();

            row1.selectOff();
            row2.selectOff();
            row3.selectOff();
        }
    }
}
