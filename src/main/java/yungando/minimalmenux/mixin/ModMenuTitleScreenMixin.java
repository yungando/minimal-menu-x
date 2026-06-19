package yungando.minimalmenux.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import yungando.minimalmenux.MinimalMenuX;

@Pseudo
@Mixin(value = TitleScreen.class, priority = 1500, remap = false)
public abstract class ModMenuTitleScreenMixin extends Screen {
  protected ModMenuTitleScreenMixin(Component title) {super(title);}

  @TargetHandler(mixin = "com.terraformersmc.modmenu.mixin.MixinTitleScreen", name = "addModMenuIconWidget")
  @ModifyReturnValue(method = "getHorizontalPosition", at = @At(value = "RETURN"))
  private int setModMenuHorizonPos(int original) {
    if (MinimalMenuX.config.reduceIconWidgets()) {
      return (this.width / 2) + 104;
    }

    return original;
  }
}
