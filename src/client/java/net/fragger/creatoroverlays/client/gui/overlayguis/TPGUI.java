package net.fragger.creatoroverlays.client.gui.overlayguis;

import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.fabricmc.fabric.api.util.TriState;
import net.fragger.creatoroverlays.creatoroverlays;
import net.fragger.creatoroverlays.util.config.COConfigs;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.client.TPHandler.tpArraySize;
import static net.fragger.creatoroverlays.client.TPHandler.tpGUIRows;
import static net.fragger.creatoroverlays.client.gui.RootGUI.*;
import static net.fragger.creatoroverlays.event.KeyInputHandler.coScreen;
import static net.fragger.creatoroverlays.event.KeyInputHandler.tpOverlay;

public class TPGUI extends WPlainPanel {

    public static final Identifier OUTLINE = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/tp_outline.png");

    public static final Identifier MULTIPOINT_BLACK = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/multi_point_black.png");
    public static final Identifier MULTIPOINT_WHITE = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/multi_point_white.png");
    public static final Identifier MULTIPOINT_RED = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/multi_point_red.png");
    public static final Identifier MULTIPOINT_BLUE = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/multi_point_blue.png");

    public static WPlainPanel tpPanel = new WPlainPanel();

    public static WToggleButton toggleAll = new WToggleButton(TOGGLE_ON, TOGGLE_OFF);
    public static WButton multiSelection;

    private static WSprite outline = new WSprite(OUTLINE);

    private final WPlainPanel pointPanel = new WPlainPanel();

    private static WScrollPanel scrollPanel;

    private static int selectedColor = 0;

    public TPGUI() {

        tpPanel.setSize(guiWidth, guiHeight);



        WButton addOverlay = new WButton(Text.literal("+"));
        tpPanel.add(addOverlay, 10, 10, 20, 20);
        addOverlay.setOnClick(() -> {
            tpOverlay.addTP();
        });

        WLabel head = new WLabel(Text.translatable("key.creatoroverlays.gui.trackingpoints"));
        head.setHorizontalAlignment(HorizontalAlignment.CENTER);
        tpPanel.add(head, 115, 10);

        multiSelection = new WButton(new TextureIcon(MULTIPOINT_BLACK));
        tpPanel.add(multiSelection, 210, 10, 20, 20);
        multiSelection.setOnClick(() -> {
           tpOverlay.selectSameColor(selectedColor);
        });

        scrollPanel = new WScrollPanel(pointPanel);
        scrollPanel.setScrollingVertically(TriState.TRUE);
        tpPanel.add(scrollPanel, 10, 40, 230, 150);

        tpPanel.add(outline, 10, 38, 222, 154);

        //toggle all
        tpPanel.add(toggleAll, 10, 200, 18, 18);
        toggleAll.setOnToggle(on -> {
            tpOverlay.updateRenderStatus();
        });

        //close button
        WButton close = new WButton(Text.translatable("key.creatoroverlays.gui.close"));
        tpPanel.add(close, 200, 200, 40, 20);
        close.setOnClick(() -> {
            coScreen.close();
            COConfigs.writeConfigs();
        });

    }

    public void addRow(int i) {
        TPGUIRow newRow = new TPGUIRow(Text.of("Tracking Point " + (i + 1)));
        pointPanel.setSize(220, tpGUIRows.size() * 35);
        pointPanel.add(newRow.rowPanel(), 0, ((i + 1) * 35) - 25);
        tpGUIRows.add(newRow);
        tpPanel.remove(scrollPanel);
        scrollPanel = new WScrollPanel(pointPanel);
        scrollPanel.setScrollingVertically(TriState.TRUE);
        tpPanel.add(scrollPanel, 10, 40, 230, 150);
    }

    public void removeRow(TPGUIRow row) {
        for (TPGUIRow tpGUIRow : tpGUIRows) {
            pointPanel.remove(tpGUIRow.rowPanel());
        }
        tpGUIRows.remove(row);
        row.getTP().setisRendered(false);
        tpOverlay.updateArray();
        tpPanel.remove(scrollPanel);

        for (int i = 0; i < tpGUIRows.size(); i++) {
            pointPanel.add(tpGUIRows.get(i).rowPanel(), 0, ((i + 1) * 35) - 25);
        }
        pointPanel.setSize(220, tpGUIRows.size() * 35);
        scrollPanel = new WScrollPanel(pointPanel);
        scrollPanel.setScrollingVertically(TriState.TRUE);
        tpPanel.add(scrollPanel, 10, 40, 230, 150);
        if(tpArraySize <= 0) {
            tpOverlay.toggleAllOff();
        }
    }
    public void multiSelect() {
        updateMultiSelection(tpOverlay.getSelected().color());
    }
    public void updateMultiSelection(int color) {
        selectedColor = color;
        if (color == 1) {
            multiSelection.setIcon(new TextureIcon(MULTIPOINT_WHITE));
        } else if (color == 2) {
            multiSelection.setIcon(new TextureIcon(MULTIPOINT_RED));
        } else if (color == 3) {
            multiSelection.setIcon(new TextureIcon(MULTIPOINT_BLUE));
        } else {
            multiSelection.setIcon(new TextureIcon(MULTIPOINT_BLACK));
        }
    }
    public WButton multiSelectButton() {
        return multiSelection;
    }
}
