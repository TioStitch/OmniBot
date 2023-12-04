package the.ominibot.discord.model.entities;

import the.ominibot.discord.model.OmniUser;
import the.ominibot.discord.data.OmniData;
import the.ominibot.discord.model.dao.UserDao;
import the.ominibot.discord.utils.exceptions.OmniDataException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserFactory implements UserDao {

    private final Connection conn;

    public UserFactory() {
        conn = new OmniData().getConnection();
    }


    @Override
    public void insert(OmniUser user) {
        try (PreparedStatement st = conn.prepareStatement("INSERT INTO player "
                + "(Name, Daily, Work, Omnitrix, DiscordID) "
                + "VALUES "
                + "(?, ?, ?, ?, ?)")) {

            st.setString(1, user.getName());
            st.setObject(2, LocalDateTime.now());
            st.setString(3, user.getWork());
            st.setString(4, user.getOmnitrix());
            st.setLong(5, user.getDiscordID());

            st.executeUpdate();
            System.out.println("[SQLITE] O Banco de Dados teve um usuário inserido:");
            System.out.println("[SQLITE] Usuário: " + user.getName());

        } catch (SQLException e) {
            throw new OmniDataException(e.getMessage());
        }
    }

    @Override
    public void update(OmniUser user) {
        try (PreparedStatement st = conn.prepareStatement("UPDATE player "
                + "SET Name = ?, Daily = ?, Work = ?, Omnitrix = ?, DiscordID = ? "
                + "WHERE Id = ?")) {

            st.setString(1, user.getName());
            st.setObject(2, LocalDateTime.now());
            st.setString(3, user.getWork());
            st.setString(4, user.getOmnitrix());
            st.setLong(5, user.getDiscordID());
            st.setInt(6, user.getID());

            st.executeUpdate();
            System.out.println("[SQLITE] O Banco de Dados teve um usuário atualizado:");
            System.out.println("[SQLITE] Usuário: " + user.getName());

        } catch(SQLException e) {
            throw new OmniDataException(e.getMessage());
        }
    }

    @Override
    public void deleteByID(int id) {
        try (PreparedStatement st = conn.prepareStatement("DELETE FROM player "
                + "WHERE Id = ?")) {

            st.setInt(1, id);

            st.executeUpdate();

            System.out.println("[SQLITE] O Banco de Dados teve um usuário deletado: ");
            System.out.println("[SQLITE] O ID do usuário era: " + id);
        } catch (SQLException e) {
            throw new OmniDataException(e.getMessage());
        }
    }

    @Override
    public OmniUser getUserByDiscord(long id) {
        try (PreparedStatement st = conn.prepareStatement("SELECT player.* "
                + "FROM player "
                + "WHERE player.DiscordID = ?")) {

            st.setLong(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return initOmniUser(st);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new OmniDataException(e.getMessage());
        }
    }

    @Override
    public OmniUser getUserByID(int id) {
        try (PreparedStatement st = conn.prepareStatement("SELECT player.* "
                + "FROM player "
                + "WHERE player.Id = ?")) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return initOmniUser(st);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new OmniDataException(e.getMessage());
        }
    }

    private OmniUser initOmniUser(PreparedStatement st) throws SQLException {
        OmniUser user = new OmniUser();
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                LocalDateTime localDateTime = LocalDateTime.now();
                String formattedDateTime = localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS"));
                Timestamp timestamp = Timestamp.valueOf(formattedDateTime);

                user.setName(rs.getString("Name"));
                user.setDaily(timestamp.toLocalDateTime());
                user.setWork(rs.getString("Work"));
                user.setOmnitrix(rs.getString("Omnitrix"));
                user.setDiscordID(rs.getLong("DiscordID"));
                user.setID(rs.getInt("Id"));
                return user;
            }
            return user;
        }
    }
}
