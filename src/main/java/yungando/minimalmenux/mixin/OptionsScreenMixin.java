package yungando.minimalmenux.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import yungando.minimalmenux.MinimalMenuX;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin {

  @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;", ordinal = 4))
  private <T extends LayoutElement> LayoutElement removeLanguageButton(GridLayout.RowHelper instance, T widget, Operation<LayoutElement> original) {
    return MinimalMenuX.config.hideLanguage()
      ? widget
      : original.call(instance, widget);
  }

  @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;", ordinal = 5))
  private <T extends LayoutElement> LayoutElement removeChatButton(GridLayout.RowHelper instance, T widget, Operation<LayoutElement> original) {
    return MinimalMenuX.config.hideChat()
      ? widget
      : original.call(instance, widget);
  }

  @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;", ordinal = 7))
  private <T extends LayoutElement> LayoutElement removeAccessibilityButton(GridLayout.RowHelper instance, T widget, Operation<LayoutElement> original) {
    return MinimalMenuX.config.hideAccessibility()
      ? widget
      : original.call(instance, widget);
  }

  @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;", ordinal = 8))
  private <T extends LayoutElement> LayoutElement removeTelemetryButton(GridLayout.RowHelper instance, T widget, Operation<LayoutElement> original) {
    return MinimalMenuX.config.hideTelemetry()
      ? widget
      : original.call(instance, widget);
  }

  @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;", ordinal = 9))
  private <T extends LayoutElement> LayoutElement removeCreditsButton(GridLayout.RowHelper instance, T widget, Operation<LayoutElement> original) {
    return MinimalMenuX.config.hideCredits()
      ? widget
      : original.call(instance, widget);
  }
}
