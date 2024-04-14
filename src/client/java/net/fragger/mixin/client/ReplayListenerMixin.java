package net.fragger.mixin.client;

import com.replaymod.core.Module;
import com.replaymod.core.ReplayMod;
import com.replaymod.core.ReplayModBackend;
import net.fragger.creatoroverlays.event.ReplayListener;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ReplayMod.class, remap = false)
public class ReplayListenerMixin {
    @Shadow
    private List<Module> modules;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void constructorInject(ReplayModBackend backend, CallbackInfo ci) {
        this.modules.add(new ReplayListener());
    }
}
