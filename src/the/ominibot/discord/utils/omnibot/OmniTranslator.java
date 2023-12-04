package the.ominibot.discord.utils.omnibot;

public class OmniTranslator {

    public String translate(String type) {
        switch (OmnitrixTypes.valueOf(type)) {
            case OMNITRIX_PROTOTYPE:
                return "Protótipo do Omnitrix";
            case SUPEROMNITRIX:
                return "Superomnitrix";
            case BIOMNITRIX:
                return "Biomnitrix";
            case NEMETRIX:
                return "Nemetrix";
            case ANTITRIX:
                return "Antitrix";
            default:
                return "Omnitrix Básico";
        }
    }
}
