package namidevelopment.kiriyaga.templateplugin.client.impl.command;

import namidevelopment.kiriyaga.api.annotation.RegisterCommand;
import namidevelopment.kiriyaga.api.model.command.Command;
import namidevelopment.kiriyaga.api.model.command.CommandArgument;
import net.minecraft.network.chat.Component;

import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.model.setting.KeyBindSetting;

import static namidevelopment.kiriyaga.api.NamiApi.*;

public class UnbindAllCommand extends Command {

    public UnbindAllCommand() {
        super("unbindall", new CommandArgument[0]);
    }

    @Override
    public void execute(Object[] parsedArgs) {

        int count = 0;
        for (Feature feature : FEATURE_SERVICE.getStorage().getAll()) {
            for (var setting : feature.getSettings()) {
                if (setting instanceof KeyBindSetting bindSetting) {
                    if (bindSetting.get() != 0) {
                        bindSetting.set(0);
                        count++;
                    }
                }
            }
        }

        Component msg = CAT_FORMAT.format("{gray}Removed {global}" + count + " {gray}binds from all your features.");
        CHAT_SERVICE.sendPersistent(UnbindAllCommand.class.getName(), msg);

        CONFIG_SERVICE.saveFeatures();
    }
}
