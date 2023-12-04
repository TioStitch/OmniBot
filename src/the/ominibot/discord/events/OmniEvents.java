package the.ominibot.discord.events;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import the.ominibot.discord.addons.AddonsManager;
import the.ominibot.discord.addons.sumo.Sumo_Addon;
import the.ominibot.discord.data.OmniData;
import the.ominibot.discord.model.OmniUser;
import the.ominibot.discord.model.DaoFactory;
import the.ominibot.discord.model.dao.UserDao;
import the.ominibot.discord.utils.omnibot.OmniTranslator;
import the.ominibot.discord.utils.omnibot.OmnitrixTypes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

public class OmniEvents extends ListenerAdapter {

    private final Sumo_Addon sumo = new AddonsManager(false).getSumoAddon();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "ping":
                obtainPing(event);
                break;
            case "perfil":
                obtainUserInfo(event);
                break;
            case "criar":
                createUser(event);
                break;
            case "sumo":
                obtainYourDaily(event);
                break;
            default:
                System.out.printf("Um comando não existente não foi executado!");
        }
    }

    private void obtainPing(SlashCommandInteractionEvent event) {
        Member member = event.getMember();

        if (member != null) {
            long time = System.currentTimeMillis();
            sendMessage(event, "O ping atual é de: " + (System.currentTimeMillis() - time), "ping");
        }
    }

    private void obtainUserInfo(SlashCommandInteractionEvent event) {
        Member member = event.getOption("user", OptionMapping::getAsMember);

        if (member == null) {
            return;
        }

        UserDao userDao = DaoFactory.createUserDao();
        OmniUser user = userDao.getUserByDiscord(member.getIdLong());

        if (user == null) {
            sendMessage(event, "[PRIMUS] O usuário não existe em nosso banco de dados!", "create");
            return;
        }

        OmniTranslator translator = new OmniTranslator();

        StringBuilder builder = new StringBuilder("**USUÁRIO ENCONTRADO**\n");
        builder.append("Usuário: " + user.getName() + "\n");
        builder.append("Último daily: " + user.getDaily() + "\n");
        builder.append("Trabalho: " + user.getWork() + "\n");
        builder.append("Omnitrix: " + translator.translate(user.getOmnitrix()) + "\n");
        builder.append("ID Discord: " + user.getDiscordID() + "\n");

        sendMessage(event, builder.toString(), "create");
    }

    private void obtainYourDaily(SlashCommandInteractionEvent event) {
        Member member = event.getMember();

        if (member == null) {
            return;
        }

        UserDao userDao = DaoFactory.createUserDao();
        OmniUser user = userDao.getUserByDiscord(member.getIdLong());

        if (user == null) {
            sendMessage(event, "[PRIMUS] O usuário não existe em nosso banco de dados!", "sumo");
            return;
        }

        if (!hasAvailable(user)) {
            sendMessage(event, "[PRIMUS] Você já realizou uma coleta de cartas de sumô hoje!", "sumo");
            return;
        }

        Random random = new Random();
        int genCards = Math.min(350, random.nextInt(350));

        StringBuilder builder = new StringBuilder("**CARTAS DE SUMÔ DIÁRIAS**\n");
        builder.append("Me parece que hoje você recebeu as suas\n");
        builder.append("cartas sumô! Volte novamente amanhã para\n");
        builder.append("poder receber novas cartas sumô.\n");
        builder.append("\n");
        builder.append("**Cartas Coletadas:** " + genCards + " \n");
        builder.append("**Total de cartas:** 512 \n\n");

        builder.append("**Última Coleta:** " + user.getDaily() + " \n");
        LocalDateTime newLocalTime = LocalDateTime.now();
        user.setDaily(newLocalTime);
        builder.append("**Tempo entre Coletas:** " + hoursDiference(user.getDaily(), newLocalTime) + "\n");

        userDao.update(user);

        sendMessage(event, builder.toString(), "sumo");
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


    private void createUser(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        OmnitrixTypes yourOmnitrix = OmnitrixTypes.valueOf(event.getOption("omnitrix").getAsString());

        if (member == null) {
            return;
        }

        UserDao userDao = DaoFactory.createUserDao();
        OmniUser user = userDao.getUserByDiscord(member.getIdLong());

        if (user != null) {
            sendMessage(event, "[PRIMUS] O seu usuário já foi criado!", "create");
            return;
        }

        OmniUser userToNew = new OmniUser(member.getNickname(), LocalDateTime.now(), "Encanador", yourOmnitrix.toString(), member.getIdLong());
        userDao.insert(userToNew);

        sendMessage(event, "[PRIMUS] O seu usuário foi criado!", "create");
    }

    private void sendMessage(SlashCommandInteractionEvent event, String message, String content) {
        event.reply(content).setEphemeral(true)
                .flatMap(v -> event.getHook().editOriginalFormat(message)
                ).queue();
    }
}
