package yungando.minimalmenux.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import yungando.minimalmenux.MinimalMenuX;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
  protected TitleScreenMixin(Component title) {
    super(title);
  }

  @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/TitleScreen;createNormalMenuOptions(II)I"), index = 0)
  private int setTopPosForNormalMenuOptions(int topPos) {
    return MinimalMenuX.config.hideRealms()
      ? topPos + 24
      : topPos;
  }

  @ModifyReturnValue(method = "createNormalMenuOptions", at = @At(value = "RETURN"))
  private int setNormalMenuOptionsReturnTopPos(int topPos) {
    int spacing = 0;

    if (MinimalMenuX.config.hideRealms()) {
      spacing -= 24;
    }

    if (MinimalMenuX.config.reduceIconWidgets()) {
      spacing -= 24;
    }

    return topPos + spacing;
  }

  @WrapOperation(method = "createNormalMenuOptions", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/TitleScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;", ordinal = 3))
  private GuiEventListener removeRealmsButton(TitleScreen instance, GuiEventListener guiEventListener, Operation<GuiEventListener> original) {
    return MinimalMenuX.config.hideRealms()
      ? guiEventListener
      : original.call(instance, guiEventListener);
  }

  @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/TitleScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;", ordinal = 1))
  private GuiEventListener removeLanguageButton(TitleScreen instance, GuiEventListener guiEventListener, Operation<GuiEventListener> original) {
    return MinimalMenuX.config.reduceIconWidgets()
      ? guiEventListener
      : original.call(instance, guiEventListener);
  }

  @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/TitleScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;", ordinal = 2))
  private GuiEventListener removeAccessibilityButton(TitleScreen instance, GuiEventListener guiEventListener, Operation<GuiEventListener> original) {
    return MinimalMenuX.config.reduceIconWidgets()
      ? guiEventListener
      : original.call(instance, guiEventListener);
  }

  @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/FriendsButton;setPosition(II)V"), index = 0)
  private int setHorizontalPosForFriendsButton(int horizontalPos) {
    return MinimalMenuX.config.reduceIconWidgets()
      ? (this.width / 2) - 124
      : horizontalPos;
  }
  
  @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/Button$Builder;bounds(IIII)Lnet/minecraft/client/gui/components/Button$Builder;"), index = 1)
  private int setFinalButtonRowVerticalPos(int topPos) {
    return MinimalMenuX.config.reduceIconWidgets()
      ? topPos + 12
      : topPos;
  }

  @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/TitleScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;", ordinal = 5))
  private GuiEventListener removeCopyright(TitleScreen instance, GuiEventListener guiEventListener, Operation<GuiEventListener> original) {
    return MinimalMenuX.config.hideCopyright()
      ? guiEventListener
      : original.call(instance, guiEventListener);
  }
}