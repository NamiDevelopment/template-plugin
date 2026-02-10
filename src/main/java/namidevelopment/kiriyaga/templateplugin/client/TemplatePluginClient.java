package namidevelopment.kiriyaga.templateplugin.client;

import namidevelopment.kiriyaga.api.model.plugin.Plugin;
import namidevelopment.kiriyaga.templateplugin.client.impl.command.*;
import namidevelopment.kiriyaga.templateplugin.client.impl.feature.*;
import namidevelopment.kiriyaga.templateplugin.client.impl.hudfeature.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import static namidevelopment.kiriyaga.api.NamiApi.*;
import static namidevelopment.kiriyaga.api.NamiApi.CONFIG_SERVICE;

public class TemplatePluginClient implements ClientModInitializer {

    public static final String VERSION;
    public static final String PLUGIN_NAME = "Example Plugin";
    public static final String AUTHORS = "Kiriyaga7615";
    public static final int PLUGIN_ID = 256;

    static {
        ModContainer mod = FabricLoader.getInstance().getModContainer("nami-template-plugin").orElse(null);
        if (mod != null) {
            VERSION = mod.getMetadata().getVersion().getFriendlyString();
        } else {
            VERSION = "dev-environment";
        }
    }


    @Override
    public void onInitializeClient() {
        Plugin plugin = new Plugin(PLUGIN_ID, PLUGIN_NAME, VERSION, AUTHORS);

        // FEATURES
        plugin.getRegisteredFeatures().add(new ExampleFeature());

        // HUD FEATURES
        plugin.getRegisteredFeatures().add(new GreetingFeature());
        plugin.getRegisteredFeatures().add(new HelloHudFeature());

        // COMMANDS
        plugin.getRegisteredCommands().add(new UnbindAllCommand());

        // You need to register plugin here
        PLUGIN_SERVICE.registerPlugin(plugin);

        // You can force enabling your client from start, config will still disable it if its not first launch
        // Please keep it clear, dont touch some core api functionality you dont know about, dont force people to load anything they cant disable
        //PLUGIN_SERVICE.enablePlugin(PLUGIN_ID);

        LOGGER.info(NAME + " " + VERSION + " has been initialized\n");

        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            // add here everything you need to do after resource loaded
        });

        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            // add here everything you need to do before client shutdown
        });
    }
}
