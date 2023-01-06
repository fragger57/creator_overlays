package net.fragger.creatoroverlays.client;

public class RenderHandler {

    public static int color = 0;
    public static int rotation = 0;
    public static boolean isRO3Rendered = false;
    public static boolean isGRRendered = false;
    public static boolean isVVRendered = false;
    public static boolean isRO3VV = false;
    public static boolean isGRVV = false;

    public static void updateRO3Status() {
        //updates render status of overlay
        if (isVVRendered) {
            if (isGRVV) {
                isGRVV = false;
            }
            if (isRO3VV) {
                isRO3VV = false;
            } else {
                isRO3VV = true;
            }
            if (isRO3Rendered) {
                isRO3VV = true;
                isRO3Rendered = false;
            }
        } else {
            if (isRO3Rendered) {
                isRO3Rendered = false;
            } else {
                isRO3Rendered = true;
            }
        }
    }
    public static void updateGRStatus() {
        if (isVVRendered) {
            if (isRO3VV) {
                isRO3VV = false;
            }
            if (isGRVV) {
                isGRVV = false;
            } else {
                isGRVV = true;
            }
            if (isGRRendered) {
                isGRVV = true;
                isGRRendered = false;
            }
        } else {
            if (isGRRendered) {
                isGRRendered = false;
            } else {
                isGRRendered = true;
            }
        }
    }
    public static void updateVVStatus() {
        if (isVVRendered) {
            isVVRendered = false;
            if (isRO3VV) {
                isRO3VV = false;
                isRO3Rendered = true;
            }
            if (isGRVV) {
                isGRVV = false;
                isGRRendered = true;
            }
        } else {
            isVVRendered = true;
            if (isRO3Rendered) {
                isRO3Rendered = false;
                isRO3VV = true;
            }
            if (isGRRendered) {
                isGRRendered = false;
                isGRVV = true;
            }
        }
    }

    public static void colorCycle() {
        //0 is black, 1 is white, and 2 is red
        if (color == 0) {
            color = 1;
        } else if (color == 1) {
            color = 2;
        } else {
            color = 0;
        }
    }
    public static void rotateUp() {
        //cycles rotation up
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
        //cycles rotation down
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
