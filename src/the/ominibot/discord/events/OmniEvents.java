package the.ominibot.discord.events;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import the.ominibot.discord.model.OmniUser;
import the.ominibot.discord.model.DaoFactory;
import the.ominibot.discord.model.dao.UserDao;

import java.util.Date;

public class OmniEvents extends ListenerAdapter {

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

        StringBuilder builder = new StringBuilder("**USUÁRIO ENCONTRADO**\n");
        builder.append("Usuário: " + user.getName() + "\n");
        builder.append("Último daily: " + user.getDaily() + "\n");
        builder.append("Trabalho: " + user.getWork() + "\n");
        builder.append("Omnitrix: " + user.getOmnitrix() + "\n");
        builder.append("ID Discord: " + user.getDiscordID() + "\n");

        sendMessage(event, builder.toString(), "create");
    }


    private void createUser(SlashCommandInteractionEvent event) {
        Member member = event.getMember();

        if (member == null) {
            return;
        }

        UserDao userDao = DaoFactory.createUserDao();
        OmniUser user = userDao.getUserByDiscord(member.getIdLong());

        if (user != null) {
            sendMessage(event, "[PRIMUS] O seu usuário já foi criado!", "create");
            return;
        }

        OmniUser userToNew = new OmniUser(member.getNickname(), new Date(), "Encanador", "Classic", member.getIdLong());
        userDao.insert(userToNew);

        sendMessage(event, "[PRIMUS] O seu usuário foi criado!", "create");
    }

    private void sendMessage(SlashCommandInteractionEvent event, String message, String content) {
        event.reply(content).setEphemeral(true)
                .flatMap(v -> event.getHook().editOriginalFormat(message)
                ).queue();
    }
}
