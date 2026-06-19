package yungando.minimalmenux.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import yungando.minimalmenux.MinimalMenuX;

@Pseudo
@Mixin(value = TitleScreen.class, priority = 1500, remap = false)
public abstract class MainMenuCreditsTitleScreenMixin extends Screen {
  protected MainMenuCreditsTitleScreenMixin(Component title) {super(title);}

  @TargetHandler(mixin = "dev.isxander.mainmenucredits.mixins.TitleScreenMixin", name = "addText")
  @ModifyArg(
    method = "@MixinSquared:Handler",
    at = @At(value = "INVOKE", target = "Ldev/isxander/mainmenucredits/gui/MMCPlainTextButton;<init>(IIIILnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/Font;Z)V"),
    slice = @Slice(from = @At(value = "INVOKE", target = "Ldev/isxander/mainmenucredits/config/MMCConfigEntry;getBottomRight()Ljava/util/List;")),
    index = 3
  )
  private int setBottomRightCreditsHorizontalPos(int horizontalHeight) {
    return MinimalMenuX.config.reduceIconWidgets()
      ? horizontalHeight + 20
      : horizontalHeight;
  }
}
