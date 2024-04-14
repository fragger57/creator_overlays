package net.fragger.creatoroverlays.client;

import net.fragger.creatoroverlays.util.config.COConfigs;

public abstract class StaticOverlay extends AbstractOverlay {

   public int color = COConfigs.OVERLAY_COLOR;
   public int rotation = 0;

   public boolean state = true;

   //0 is black, 1 is white, and 2 is red
   public void colorCycle() {
       if (color == 1) {
           color = 2;
       } else if (color == 2) {
           color = 0;
       } else {
           color = 1;
       }
   }

   public void rotateUp() {
       if (rotation == 1) {
           rotation = 2;
       } else if (rotation == 2) {
           rotation = 3;
       } else if (rotation == 3) {
           rotation = 0;
       } else {
           rotation = 1;
       }
   }
   public void rotateDown() {
       if (rotation == 180) {
           rotation = 90;
       } else if (rotation == 270) {
           rotation = 180;
       } else if (rotation == 0) {
           rotation = 270;
       } else {
           rotation = 0;
       }
   }

   public void swap() {
       state = !state;
   }

   public int color(){
        return color;
    }
}
