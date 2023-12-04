package the.ominibot.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import the.ominibot.discord.addons.AddonsManager;
import the.ominibot.discord.events.OmniEvents;
import the.ominibot.discord.events.OmniKey;
import the.ominibot.discord.data.OmniData;
import the.ominibot.discord.model.DaoFactory;
import the.ominibot.discord.model.OmniUser;
import the.ominibot.discord.model.dao.UserDao;
import the.ominibot.discord.model.entities.UserFactory;
import the.ominibot.discord.utils.omnibot.OmniState;

import javax.swing.*;

public class OmniMain extends JFrame {
    private final static OmniSettings settings = new OmniSettings();
    private static JDABuilder builder = JDABuilder.createDefault(settings.getTOKEN());
    public static JDA jda;

    private final OmniKey omniKey = new OmniKey();


    public OmniMain(boolean load) {

        OmniData database = new OmniData();
        database.connect();

        database.createPlayerTable();

        if (load) {
            loadMenu();
        }
    }

    public static void main(String[] args) {
        new OmniMain(true);
    }

    private void loadMenu() {

        setTitle("OmniBot | Dashboard");
        setSize(950,  640);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setIconImage(new ImageIcon(getClass().getResource("assets/OmniLogo.png")).getImage());
        add(new OmniPanel(true));
        addMouseListener(omniKey);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void loadBot() {
        builder = JDABuilder.createDefault(settings.getTOKEN());
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);

        builder.setAutoReconnect(false);

        builder.setStatus(settings.getVISIBILITY());
        builder.setActivity(Activity.of(settings.getSTATUS(), settings.getMESSAGE()));

        builder.addEventListeners(new OmniEvents());

        jda = builder.build();

        jda.updateCommands()
                .addCommands(
                        Commands.slash("ping", "Comando de visualizar ping!"),
                        Commands.slash("perfil", "Comando de gerenciar Usuário!")
                                .addOption(OptionType.USER, "user", "Veja as informações do usuário", true),
                        Commands.slash("criar", "Cria o perfil de Usuário!")
                                .addOptions(
                                        new OptionData(OptionType.STRING, "omnitrix", "Escolha o seu Omnitrix")
                                                .addChoice("Protótipo", "OMNITRIX_PROTOTYPE")
                                                .addChoice("Superomnitrix", "SUPEROMNITRIX")
                                                .addChoice("Biomnitrix", "BIOMNITRIX")
                                                .addChoice("Nemetrix", "NEMETRIX")
                                                .addChoice("Antitrix", "ANTITRIX")))
                .queue();


        new AddonsManager(true);
        Runtime.getRuntime().addShutdownHook(new Thread(this::unloadBot));
    }

    public void unloadBot() {
        try {
            Thread.sleep(5000); // Aguarde 5 segundos antes de chamar jda.shutdownNow()
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        jda.shutdown();
    }

    public OmniKey getOmniKey() {
        return omniKey;
    }


    public OmniSettings getSettings() {
        return settings;
   }

    public JDA getJDA() {
        return jda;
    }
}
