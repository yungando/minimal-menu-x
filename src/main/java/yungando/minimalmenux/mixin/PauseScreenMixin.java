package yungando.minimalmenux.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.FriendsButton;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.layouts.LayoutSettings;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yungando.minimalmenux.MinimalMenuX;
import yungando.minimalmenux.interfaces.PauseScreenInterface;

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin extends Screen implements PauseScreenInterface {
  protected PauseScreenMixin(Component title) {
    super(title);
  }

  @Unique
  private AbstractWidget pauseScreenModMenuButton;
  @Unique
  private boolean pauseScreenModMenuButtonInsertStyle;

  @Override
  public void minimal_menu_x$setPauseScreenModMenuButton(AbstractWidget button) {
    this.pauseScreenModMenuButton = button;
  }

  @Override
  public void minimal_menu_x$setPauseScreenModMenuButtonInsertStyle(boolean insertStyle) {
    this.pauseScreenModMenuButtonInsertStyle = insertStyle;
  }

  @Shadow
  private FriendsButton friends;

  @WrapOperation(method = "createPauseMenu", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;ILnet/minecraft/client/gui/layouts/LayoutSettings;)Lnet/minecraft/client/gui/layouts/LayoutElement;", ordinal = 1))
  private <T extends LayoutElement> LayoutElement skipAddingIconRow(GridLayout.RowHelper instance, T widget, int columnWidth, LayoutSettings layoutSettings, Operation<T> original) {
    return MinimalMenuX.config.reducePauseScreenIconWidgets()
      ? widget
      : original.call(instance, widget, columnWidth, layoutSettings);
  }

  @Inject(method = "createPauseMenu", at = @At("TAIL"))
  private void reAddFriendsButton(CallbackInfo ci) {
    if (!MinimalMenuX.config.reducePauseScreenIconWidgets()) return;

    int x = (this.width / 2);
    int y = (this.height / 4) + 63;

    if (pauseScreenModMenuButtonInsertStyle) {
      friends.setPosition(x - 126, y - 6);
    } else {
      friends.setPosition(x - 126, y);
    }
    this.addRenderableWidget(friends);

    if (pauseScreenModMenuButton != null) {
      pauseScreenModMenuButton.setPosition(x + 106, y);
      this.addRenderableWidget(pauseScreenModMenuButton);
    }
  }
}
