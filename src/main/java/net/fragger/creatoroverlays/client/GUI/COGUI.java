package net.fragger.creatoroverlays.client.gui;

import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.fabricmc.fabric.api.util.TriState;
import net.fragger.creatoroverlays.creatoroverlays;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.fragger.creatoroverlays.client.gui.RootGUI.guiHeight;
import static net.fragger.creatoroverlays.client.gui.RootGUI.guiWidth;
import static net.fragger.creatoroverlays.event.KeyInputHandler.coScreen;

public class COGUI extends AbstractGUI {

    private static final Identifier NotesOutline = new Identifier(creatoroverlays.MOD_ID,"textures/widgets/patch_notes_outline.png");

    public WPlainPanel coPanel = new WPlainPanel();
    private WPlainPanel notes = new WPlainPanel();

    public COGUI() {

        coPanel.setSize(guiWidth, guiHeight);

        //header section
        WLabel head = new WLabel(Text.translatable("key.creatoroverlays.gui.creatoroverlays"));
        head.setHorizontalAlignment(HorizontalAlignment.CENTER);
        coPanel.add(head, 120, 10);

        WLabel version = new WLabel(Text.literal("Version: 3.0.0"));
        coPanel.add(version, 10, 30);
        WLabel release = new WLabel(Text.literal("Released:" + " December 2023"));
        coPanel.add(release, 10, 40);

        WLabel patch = new WLabel(Text.literal("Patch Notes:"));
        coPanel.add(patch, 10, 60);

        WSprite outline = new WSprite(NotesOutline);
        coPanel.add(outline, 10, 75, 222, 115);

        //notes panel
        notes.setSize(230, 390);

        WText intro = new WText(Text.literal("Welcome To Creator Overlays Version 3.0.0! This is the biggest update to Creator Overlays so far!" +
                " Not only does it introduce this all new GUI, but it also adds some new overlays!"));
        WLabel checkOut = new WLabel(Text.literal("Check out what else was changed below:"));
        intro.setColor(0x000000);
        checkOut.setColor(0x000000);

        notes.add(intro, 5, 2, 215, 40);
        notes.add(checkOut, 5, 50);

        addNote("Adds all new GUI for controlling Overlays", 70);
        addNote("Adds Quarters overlay to the Gridlines. Rule of Thirds is still available as the Thirds overlay", 90);
        addNote("Adds all new Facecam Overlay to see what part of the screen your Facecam will take up", 120);
        addNote("Adds all new Custom Overlay to easily add up to 4 Custom Overlays with the template resource pack available on modrinth", 150);
        addNote("Tracking Points are now stored in an Array List, allowing you to dynamically add and remove up to 16 tracking points to control", 190);
        addNote("Overlay aesthetics changed to improve usability", 230);
        addNote("Massive backend improvements to help with organization and easy expansion.", 250);

        WText end = new WText(Text.literal("If you notice any issues, or have any questions, please refer to the Creator Overlays GitHub page where you can " +
                "find the Issues page to report any bugs or other issues, as well as the Creator Overlays Wiki with information on how to use everything! " +
                "Before reporting any issues, please make sure you're using the latest version of Creator Overlays! You can find all versions, as well as links " +
                "to the GitHub page, on modrinth and CurseForge."));
        end.setColor(0x000000);
        notes.add(end, 5, 280, 215, 60);

        //scroll panel
        WScrollPanel scroll = new WScrollPanel(notes);
        scroll.setScrollingHorizontally(TriState.FALSE);
        coPanel.add(scroll, 10, 77, 230, 111);

        WButton close = new WButton(Text.translatable("key.creatoroverlays.gui.close"));
        coPanel.add(close, 200, 200, 40, 20);
        close.setOnClick(() -> {
            coScreen.close();
        });
    }

    private void addNote(String note, int y) {
        WText line = new WText(Text.literal("- " + note));
        line.setColor(0x000000);
        notes.add(line, 10, y, 210, 10);
    }
}
