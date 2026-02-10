package namidevelopment.kiriyaga.templateplugin.client.impl.feature;

import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.model.feature.FeatureCategory;
import namidevelopment.kiriyaga.api.model.setting.*;

import java.awt.*;

public class ExampleFeature extends Feature {

    public enum Page {
        MAIN,
        NUMBERS,
        MODES,
        FLAGS,
        HIDDEN
    }

    public enum ExampleMode {
        OFF,
        SIMPLE,
        ADVANCED,
        INSANE
    }

    public final EnumSetting<Page> page = addSetting(new EnumSetting<>("Page", Page.MAIN));

    public final BoolSetting enabled = addSetting(new BoolSetting("Enabled", true));
    public final ColorSetting color = addSetting(new ColorSetting("Color", new Color(255, 255, 255, 255), true));
    public final IntSetting intValue = addSetting(new IntSetting("IntValue", "Value", 5, 0, 20));
    public final DoubleSetting doubleValue = addSetting(new DoubleSetting("DoubleValue", "Value", 2.5, 0.0, 10.0));
    public final EnumSetting<ExampleMode> mode = addSetting(new EnumSetting<>("Mode", ExampleMode.SIMPLE));
    public final KeyBindSetting keybind = addSetting(new KeyBindSetting("KeyBind", -1));
    public final BoolSetting debug = addSetting(new BoolSetting("Debug", false));
    public final BoolSetting notifications = addSetting(new BoolSetting("Notifications", true));

    // You can set custom identifiers for settings to avoid id collision
    public final BoolSetting collision = addSetting(new BoolSetting("CollisionIdentifier1","Collision", false));
    public final BoolSetting collision2 = addSetting(new BoolSetting("CollisionIdentifier2","Collision", true));

    public final IntSetting hiddenInt = addSetting(new IntSetting("HiddenInt", 1337, 0, 9999));

    public ExampleFeature() {
        super("ExampleFeature", "Example feature that contains all setting types and show conditions.",
                FeatureCategory.of("Client"),
                "example", "test", "settings"
        );


        enabled.setShowCondition(() -> page.get() == Page.MAIN);
        color.setShowCondition(() -> page.get() == Page.MAIN);

        intValue.setShowCondition(() -> page.get() == Page.NUMBERS);
        doubleValue.setShowCondition(() -> page.get() == Page.NUMBERS);

        mode.setShowCondition(() -> page.get() == Page.MODES);
        keybind.setShowCondition(() -> page.get() == Page.MODES);

        debug.setShowCondition(() -> page.get() == Page.FLAGS);
        notifications.setShowCondition(() -> page.get() == Page.FLAGS);

        hiddenInt.setShowCondition(() -> page.get() == Page.HIDDEN);


        // debug only visible when notifications enabled
        debug.setShowCondition(() -> page.get() == Page.FLAGS && notifications.get());

        // keybind only visible when mode != OFF
        keybind.setShowCondition(() -> page.get() == Page.MODES && mode.get() != ExampleMode.OFF);

        // hiddenInt only visible when hiddenSetting enabled
        hiddenInt.setShowCondition(() -> page.get() == Page.HIDDEN && collision2.get());

        // You can make it just never appears, so its accessible only via commands
        hiddenInt.setShow(false);
    }
}
