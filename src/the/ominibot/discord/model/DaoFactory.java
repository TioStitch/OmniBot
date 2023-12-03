package the.ominibot.discord.model;

import the.ominibot.discord.model.dao.UserDao;
import the.ominibot.discord.model.entities.UserFactory;

public class DaoFactory {

    public static UserDao createUserDao() {
        return new UserFactory();
    }
}
