package the.ominibot.discord.model.dao;

import the.ominibot.discord.model.OmniUser;

public interface UserDao {
    void insert(OmniUser user);
    void update(OmniUser user);
    void deleteByID(int id);
    OmniUser getUserByDiscord(long id);
    OmniUser getUserByID(int id);
}
