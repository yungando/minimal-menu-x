package yungando.minimalmenux.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.modmenu.config.ModMenuConfig;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.PauseScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yungando.minimalmenux.MinimalMenuX;
import yungando.minimalmenux.interfaces.PauseScreenInterface;

@Mixin(value = PauseScreen.class, priority = 1500, remap = false)
public abstract class ModMenuPauseScreenMixin {
  @TargetHandler(mixin = "com.terraformersmc.modmenu.mixin.MixinPauseScreen", name = "insertModMenuIconButton")
  @WrapOperation(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/layouts/LinearLayout;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;"))
  private <T extends LayoutElement> T captureModMenuButton(LinearLayout instance, T child, Operation<T> original) {
    ModMenuConfig.GameMenuButtonStyle style = ModMenuConfig.GAME_MENU_BUTTON_STYLE.getValue();
    if (
      MinimalMenuX.config.reducePauseScreenIconWidgets()
      && child instanceof AbstractWidget widget
      && this instanceof PauseScreenInterface holder
      && style == ModMenuConfig.GameMenuButtonStyle.ICON
    ) {
      holder.minimal_menu_x$setPauseScreenModMenuButton(widget);
      return child;
    }

    return original.call(instance, child);
  }

  @TargetHandler(mixin = "com.terraformersmc.modmenu.mixin.MixinPauseScreen", name = "insertModMenuFullButton")
  @Inject(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;I)Lnet/minecraft/client/gui/layouts/LayoutElement;"))
  private <T extends LayoutElement> void captureModMenuButtonStyle(CallbackInfo ci, GridLayout.RowHelper helper, CallbackInfo cir) {
    ModMenuConfig.GameMenuButtonStyle style = ModMenuConfig.GAME_MENU_BUTTON_STYLE.getValue();

    if (
      style == ModMenuConfig.GameMenuButtonStyle.INSERT
      && this instanceof PauseScreenInterface holder
    ) {
      holder.minimal_menu_x$setPauseScreenModMenuButtonInsertStyle(true);
    }
  }
}
