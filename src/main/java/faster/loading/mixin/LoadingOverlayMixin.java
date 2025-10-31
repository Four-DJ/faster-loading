package faster.loading.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ReloadInstance;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.screens.LoadingOverlay;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LoadingOverlay.class)
public class LoadingOverlayMixin {
    @Shadow @Final private ReloadInstance reload;
    @Shadow @Final @Mutable private boolean fadeIn;

    @Inject(method = "render", at = @At("HEAD"))
    private void render(CallbackInfo ci) {
        fadeIn = false;
        if (reload.isDone()){
            Minecraft.getInstance().setOverlay(null);
        }
    }
}
