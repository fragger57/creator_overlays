package net.fragger.creatoroverlays.util;

public abstract class StaticOverlay extends Overlay {

   public static int color = 0;
   public static int rotation = 0;

   //0 is black, 1 is white, and 2 is red
   public static void colorCycle() {
       if (color == 0) {
           color = 1;
       } else if (color == 1) {
           color = 2;
       } else if (color == 2) {
           color = 0;
       }
   }
   public static void rotateUp() {
       if (rotation == 0) {
           rotation = 90;
       } else if (rotation == 90) {
           rotation = 180;
       } else if (rotation == 180) {
           rotation = 270;
       } else {
           rotation = 0;
       }
   }
   public static void rotateDown() {
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
}
