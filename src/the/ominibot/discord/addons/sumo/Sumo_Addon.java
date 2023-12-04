package the.ominibot.discord.addons.sumo;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import the.ominibot.discord.OmniMain;
import the.ominibot.discord.addons.impl.OmniAddon;
import the.ominibot.discord.model.OmniUser;

import java.time.Duration;
import java.time.LocalDateTime;

public class Sumo_Addon implements OmniAddon {

    public Sumo_Addon() {
        load();
    }

    public void load() {
        OmniMain.jda.upsertCommand(Commands.slash("sumo", "Obtenha a recompensa sumô!")).queue();
        System.out.println("[OMNIBOT] Comando de Sumõ adicionado!");
    }


    public boolean hasAvailable(OmniUser user) {
        long horas = hoursDiference(user.getDaily(), LocalDateTime.now());
        if (horas > 24) {
            return true;
        }
        return false;
    }

    public long hoursDiference(LocalDateTime data1, LocalDateTime data2) {
        Duration duration = Duration.between(data1, data2);
        return Math.abs(duration.toHours());
    }
}
