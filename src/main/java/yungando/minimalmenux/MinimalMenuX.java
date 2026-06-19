package yungando.minimalmenux;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import yungando.minimalmenux.config.MinimalMenuXConfig;
import yungando.minimalmenux.config.MinimalMenuXConfig.MinimalMenuXAutoConfig;

public class MinimalMenuX implements ClientModInitializer {
  public static final String MOD_ID = "minimal-menu-x";
  public static final boolean USE_AUTO_CONFIG = FabricLoader.getInstance().isModLoaded("cloth-config2");
  public static MinimalMenuXConfig config;

  @Override
  public void onInitializeClient() {
    if (USE_AUTO_CONFIG) {
      AutoConfig.register(MinimalMenuXAutoConfig.class, GsonConfigSerializer::new);
      config = AutoConfig.getConfigHolder(MinimalMenuXAutoConfig.class).getConfig();
    } else {
      config = new MinimalMenuXConfig();
    }
  }
}
