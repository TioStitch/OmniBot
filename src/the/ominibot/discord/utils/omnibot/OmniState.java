package the.ominibot.discord.utils.omnibot;

public enum OmniState {

    STARTED_BOT("assets/backgrounds/ON_Background.png"),
    STOPPED_BOT("assets/backgrounds/OFF_Background.png"),
    CONFIG_BOT("assets/backgrounds/CONFIG_Background.png");

    final String backgroundLocale;

    OmniState(String backgroundLocale) {
        this.backgroundLocale = backgroundLocale;
    }

    public String getBackgroundLocale() {
        return backgroundLocale;
    }
}
