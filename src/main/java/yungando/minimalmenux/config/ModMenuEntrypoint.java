package yungando.minimalmenux.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfigClient;
import net.fabricmc.loader.api.FabricLoader;
import yungando.minimalmenux.config.MinimalMenuXConfig.MinimalMenuXAutoConfig;

public class ModMenuEntrypoint implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return FabricLoader.getInstance().isModLoaded("cloth-config")
      ? parent -> AutoConfigClient.getConfigScreen(MinimalMenuXAutoConfig.class, parent).get()
      : ModMenuApi.super.getModConfigScreenFactory();
  }
}
