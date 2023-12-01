package net.fragger.creatoroverlays.client;

import net.minecraft.util.Identifier;

public interface Overlay {

    public void updateRenderStatus();
    public Identifier getTexture();
    public boolean isRendered();
    public void setisRendered(boolean rendered);
}
