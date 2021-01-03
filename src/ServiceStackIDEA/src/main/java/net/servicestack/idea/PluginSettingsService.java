package net.servicestack.idea;

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Layoric on 27/05/2016.
 */
@State(name = "PluginSettingsService", storages = @Storage("servicestack-settings.xml"))
public class PluginSettingsService implements PersistentStateComponent<PluginSettingsService> {
    public Boolean optOutOfStats = false;

    @Nullable
    @Override
    public PluginSettingsService getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull PluginSettingsService pluginSettingsService) {
        XmlSerializerUtil.copyBean(pluginSettingsService, this);
    }

    public static PluginSettingsService getInstance() {
        return ServiceManager.getService( PluginSettingsService.class );
    }
}
