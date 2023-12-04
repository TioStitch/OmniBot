package the.ominibot.discord.model;

import java.time.LocalDateTime;
import java.util.Date;

public class OmniUser {
    private String name;
    private LocalDateTime daily;
    private String work;
    private String omnitrix;
    private long discordID;
    private int ID;

    public OmniUser() {}

    public OmniUser(String name, LocalDateTime daily, String work, String omnitrix, long discordID) {
        this.name = name;
        this.daily = daily;
        this.work = work;
        this.omnitrix = omnitrix;
        this.discordID = discordID;
    }

    public OmniUser(String name, LocalDateTime daily, String work, String omnitrix, long discordID, int ID) {
        this.name = name;
        this.daily = daily;
        this.work = work;
        this.omnitrix = omnitrix;
        this.discordID = discordID;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
    public LocalDateTime getDaily() {
        return daily;
    }
    public String getWork() {
        return work;
    }
    public String getOmnitrix() {
        return omnitrix;
    }
    public long getDiscordID() {
        return discordID;
    }
    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDaily(LocalDateTime daily) {
        this.daily = daily;
    }
    public void setWork(String work) {
        this.work = work;
    }
    public void setOmnitrix(String omnitrix) {
        this.omnitrix = omnitrix;
    }
    public void setDiscordID(long discordID) {
        this.discordID = discordID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
}
