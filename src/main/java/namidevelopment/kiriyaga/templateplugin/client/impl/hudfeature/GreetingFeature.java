package namidevelopment.kiriyaga.templateplugin.client.impl.hudfeature;

import namidevelopment.kiriyaga.api.model.feature.HudElementFeature;
import namidevelopment.kiriyaga.api.annotation.RegisterFeature;
import namidevelopment.kiriyaga.api.model.setting.IntSetting;
import net.minecraft.network.chat.Component;

import static namidevelopment.kiriyaga.api.NamiApi.*;

public class GreetingFeature extends HudElementFeature {

    public final IntSetting greetingDelay = addSetting(new IntSetting("GreetingDelay", 240, 120, 500));
    public final IntSetting charRevealDelay = addSetting(new IntSetting("RevealDelay", 20, 15, 50));

    private static final String[] GREETINGS = {
            "Greetings %s :^)",
            "Looking great today %s :>",
            "Are ya winning son?",
            "Im like a pedo, but like a chill one :<",
            "Good evening %s :^)",
            "kids do not rename your obby",
            "OldLadyNorth did nothing wrong ^_^",
            "bro why are all these devs shooting estrogen into their morning monsters?",
            "Kiriyaga was here",
            "1.12.2 is just bad",
            "killaura pls dont kill my friends",
            "can we argue with the fact that future is a pedo client?",
            "Verify you are human...",
            "Script to win :^)",
            "dr donut buy mio client (c)",
            "Skidtrap has fallen to its poetic end (c)",
            "Do not forget to drink water %s :^)",
            "今朝毎朝",
            "Kesa MaiAsa was here",
            "phobot is not real",
            "Do not forget to get some sleep, %s!"
    };

    private String fullGreeting = "";
    private int greetingCharIndex = 0;
    private int charTickTimer = 0;
    private String currentGreeting = "";
    private boolean isFadingOut = false;
    private long greetingShownTime = 0;
    @SuppressWarnings("FieldCanBeLocal")
    private final long greetingDisplayDuration = 5000;

    public GreetingFeature() {
        super("Greeting", "Displays a greeting splash text.", 0, 0, 100, 9);
    }

    @Override
    public Component getDisplayText() {
        if (MC.player == null) {
            resetGreeting();
            return CAT_FORMAT.format("{global}NaN");
        }

        long now = System.currentTimeMillis();

        if (fullGreeting.isEmpty()) {
            String template = GREETINGS[(int) (Math.random() * GREETINGS.length)];
            try {
                fullGreeting = String.format(template, MC.player.getName().getString());
            } catch (Exception e) {
                fullGreeting = template;
            }
            greetingCharIndex = 0;
            greetingShownTime = 0;
            isFadingOut = false;
            charTickTimer = 0;
        }

        if (!isFadingOut && greetingCharIndex < fullGreeting.length()) {
            charTickTimer++;
            if (charTickTimer >= charRevealDelay.get()) {
                charTickTimer = 0;
                greetingCharIndex++;
                currentGreeting = fullGreeting.substring(0, greetingCharIndex);
            }
        } else if (!isFadingOut && greetingCharIndex == fullGreeting.length()) {
            if (greetingShownTime == 0) {
                greetingShownTime = now;
            } else if (now - greetingShownTime >= greetingDisplayDuration) {
                isFadingOut = true;
                greetingShownTime = 0;
            }
        } else if (isFadingOut) {
            if (greetingCharIndex > 0) {
                greetingCharIndex--;
                currentGreeting = fullGreeting.substring(0, greetingCharIndex);
            } else {
                fullGreeting = "";
                currentGreeting = "";
                greetingShownTime = now;
            }
        } else if (greetingCharIndex == 0 && now - greetingShownTime < greetingDelay.get() * 1000L) {
            return Component.empty();
        }

        if (currentGreeting.isEmpty()) return Component.empty();

        String formattedText = "{global}" + currentGreeting;

        width = FONT_SERVICE.getWidth(currentGreeting);
        height = FONT_SERVICE.getHeight();

        return CAT_FORMAT.format(formattedText);
    }

    private void resetGreeting() {
        fullGreeting = "";
        greetingCharIndex = 0;
        currentGreeting = "";
        greetingShownTime = 0;
        isFadingOut = false;
        charTickTimer = 0;
    }
}