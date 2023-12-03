package the.ominibot.discord;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class OmniSettings {
    public OmniSettings() {
    }

    public String getTOKEN() {
        return getBotConfig("BOT_CONFIGURATION").get("TOKEN").toString();
    }
    public String getNAME() {
        return getBotConfig("BOT_CONFIGURATION").get("NAME").toString();
    }
    public String getMESSAGE() {
        return getBotConfig("BOT_CONFIGURATION").get("MESSAGE").toString();
    }

    public Activity.ActivityType getSTATUS() {
        return Activity.ActivityType.valueOf(getBotConfig("BOT_CONFIGURATION").get("STATE").toString());
    }

    public OnlineStatus getVISIBILITY() {
        return OnlineStatus.valueOf(getBotConfig("BOT_CONFIGURATION").get("VISIBILITY").toString());
    }

    public JSONObject getBotConfig(String chavePrincipal) {
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) new JSONParser().parse(new FileReader("src/settings.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return (JSONObject) jsonObject.get(chavePrincipal);
    }
}
