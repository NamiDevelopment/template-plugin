package namidevelopment.kiriyaga.templateplugin.client.impl.hudfeature;

import namidevelopment.kiriyaga.api.model.feature.HudElementFeature;
import net.minecraft.network.chat.Component;

import static namidevelopment.kiriyaga.api.NamiApi.*;

public class HelloHudFeature extends HudElementFeature {

    public HelloHudFeature() {
        super("HelloHud", "Displays hello text.", 0, 0, 120, 9);
    }

    @Override
    public Component getDisplayText() {
        if (MC.player == null) {
            return CAT_FORMAT.format("{global}Hello NaN :^)");
        }
        String name = MC.player.getName().getString();
        String text = "Hello " + name + " :^)";
        width = FONT_SERVICE.getWidth(text);
        height = FONT_SERVICE.getHeight();

        return CAT_FORMAT.format("{global}" + text);
    }
}
