package the.ominibot.discord.addons;

import the.ominibot.discord.OmniMain;
import the.ominibot.discord.addons.sumo.Sumo_Addon;

public class AddonsManager {

    private static final OmniMain main = new OmniMain(false);
    private Sumo_Addon sumoAddon = null;

    public AddonsManager(boolean load) {
        if (load) {
            loadAddons();
        }
    }

    private void loadAddons() {
        if (main.getSettings().isActiveTheAddon("SUMO_CARD")) {
            System.out.println("[OMNIBOT] O Addon 'Cartas de Sumô' foi carregado!");
            sumoAddon = new Sumo_Addon();
        }
    }

    public Sumo_Addon getSumoAddon() {
        return sumoAddon;
    }
}
