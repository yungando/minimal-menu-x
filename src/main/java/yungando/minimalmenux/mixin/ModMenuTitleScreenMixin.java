package yungando.minimalmenux.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import yungando.minimalmenux.MinimalMenuX;

@Pseudo
@Mixin(value = TitleScreen.class, priority = 1500, remap = false)
public abstract class ModMenuTitleScreenMixin extends Screen {
  protected ModMenuTitleScreenMixin(Component title) {super(title);}

  @TargetHandler(mixin = "com.terraformersmc.modmenu.mixin.MixinTitleScreen", name = "addModMenuIconWidget")
  @ModifyArg(
    method = "@MixinSquared:Handler",
    at = @At(value = "INVOKE", target = "Lcom/terraformersmc/modmenu/gui/widget/SmallModMenuButtonWidget;<init>(IIIILnet/minecraft/network/chat/Component;IIIILnet/minecraft/client/gui/components/WidgetSprites;Lnet/minecraft/client/gui/components/Button$OnPress;Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$CreateNarration;Z)V"),
    index = 0
  )
  private int setModMenuHorizontalPos(int horizontalPos) {
    return MinimalMenuX.config.reduceTitleScreenIconWidgets()
      ? (this.width / 2) + 104
      : horizontalPos;
  }
}
