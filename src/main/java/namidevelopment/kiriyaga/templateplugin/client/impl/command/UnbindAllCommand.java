package namidevelopment.kiriyaga.templateplugin.client.impl.command;

import namidevelopment.kiriyaga.api.annotation.RegisterCommand;
import namidevelopment.kiriyaga.api.model.command.Command;
import namidevelopment.kiriyaga.api.model.command.CommandRoute;
import namidevelopment.kiriyaga.api.model.feature.Feature;
import namidevelopment.kiriyaga.api.model.setting.KeyBindSetting;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;

import static namidevelopment.kiriyaga.api.NamiApi.*;

//@RegisterCommand oops yeah its not added yet!
public class UnbindAllCommand extends Command {

    public UnbindAllCommand() {
        super("unbindall");
    }

    @Override
    public CommandRoute[] getRoutes() {
        return new CommandRoute[] {
                new CommandRoute(null)
        };
    }

    @Override
    public void execute(String route, Object[] args) {
        int count = 0;

        List<Feature> features = FEATURE_SERVICE.getStorage().getAll();
        for (Feature feature : features) {
            for (var setting : feature.getSettings()) {
                if (setting instanceof KeyBindSetting bindSetting) {
                    if (bindSetting.get() != 0) {
                        bindSetting.set(0);
                        count++;
                    }
                }
            }
        }

        MutableComponent msg = CAT_FORMAT.format("{gray}Removed {global}" + count + " {gray}binds from all your features.");
        CHAT_SERVICE.sendPersistent(this.getName(), msg);

        CONFIG_SERVICE.saveFeatures();
    }
}