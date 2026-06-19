package yungando.minimalmenux.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Category;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;
import yungando.minimalmenux.MinimalMenuX;

public class MinimalMenuXConfig {
  public boolean hideRealms() { return true; }
  public boolean reduceIconWidgets() { return true; }
  public boolean hideCopyright() { return true; }

  @Config(name = MinimalMenuX.MOD_ID)
  public static class MinimalMenuXAutoConfig extends MinimalMenuXConfig implements ConfigData {
    @Category("titleScreen")
    @Tooltip
    private boolean hideRealms = super.hideRealms();
    @Category("titleScreen")
    @Tooltip
    private boolean reduceIconWidgets = super.reduceIconWidgets();
    @Category("titleScreen")
    @Tooltip
    private boolean hideCopyright = super.hideCopyright();

    @Override
    public boolean hideRealms() { return hideRealms; }
    @Override
    public boolean reduceIconWidgets() { return reduceIconWidgets; }
    @Override
    public boolean hideCopyright() { return hideCopyright; }
  }
}
