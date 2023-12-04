package the.ominibot.discord;

import com.sun.nio.sctp.NotificationHandler;
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
        return getByOneKey("BOT_CONFIGURATION").get("TOKEN").toString();
    }
    public String getNAME() {
        return getByOneKey("BOT_CONFIGURATION").get("NAME").toString();
    }
    public String getMESSAGE() {
        return getByOneKey("BOT_CONFIGURATION").get("MESSAGE").toString();
    }

    public Activity.ActivityType getSTATUS() {
        return Activity.ActivityType.valueOf(getByOneKey("BOT_CONFIGURATION").get("STATE").toString());
    }

    public OnlineStatus getVISIBILITY() {
        return OnlineStatus.valueOf(getByOneKey("BOT_CONFIGURATION").get("VISIBILITY").toString());
    }

    public boolean isActiveTheAddon(String addon) {
        return getByTwoKey("OMNIBOT_ADDONS", addon).get("enabled").toString().equals("true");
    }


    public JSONObject getByOneKey(String chavePrincipal) {
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) new JSONParser().parse(new FileReader("src/settings.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return (JSONObject) jsonObject.get(chavePrincipal);
    }

    public JSONObject getByTwoKey(String chavePrincipal, String chaveSecundaria) {
        JSONObject jsonObject = null;
        JSONObject otherObject = null;

        try {
            jsonObject = (JSONObject) new JSONParser().parse(new FileReader("src/settings.json"));
            otherObject = (JSONObject) jsonObject.get(chavePrincipal);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return (JSONObject) otherObject.get(chaveSecundaria);
    }
}
