package yungando.minimalmenux.mixin;

import com.terraformersmc.modmenu.event.ModMenuEventHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import yungando.minimalmenux.MinimalMenuX;

@Mixin(ModMenuEventHandler.class)
public class ModMenuEventHandlerMixin {
  @ModifyArg(method = "afterTitleScreenInit", at = @At(value = "INVOKE", target = "Lcom/terraformersmc/modmenu/event/ModMenuEventHandler;buttonHasText(Lnet/minecraft/client/gui/layouts/LayoutElement;[Ljava/lang/String;)Z"), index = 1)
  private static String[] changeAnchorButton(String[] translationKeys) {
    return MinimalMenuX.config.hideRealms()
      ? new String[]{"menu.multiplayer"}
      : translationKeys;
  }
}
