package the.ominibot.discord.data;

import the.ominibot.discord.utils.exceptions.OmniDataException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OmniData {

    private static Connection conn;

    public OmniData() {
        conn = connect();
    }

  //  public Connection connect() {
  //      if (conn == null) {
  //          String url = "jdbc:sqlite:src/PlayerData.db";
  //          try (Connection connection = DriverManager.getConnection(url)) {
  //              conn = connection;
  //              System.out.println("[SQLITE] O Banco de Dados foi conectado!");
  //          } catch (SQLException e) {
  //              throw new OmniDataException(e.getMessage());
  //          }
  //      }
  //      return conn;
  //  }

    public Connection connect() {
        if (conn == null) {
            try {
                String url = "jdbc:sqlite:src/PlayerData.db";
                conn = DriverManager.getConnection(url);
                System.out.println("[SQLITE] O Banco de Dados foi conectado!");
            } catch (SQLException e) {
                throw new OmniDataException(e.getMessage());
            }
        }
        return conn;
    }

    public void createPlayerTable() {
        String url = "jdbc:sqlite:src/PlayerData.db";

        String sql = "CREATE TABLE IF NOT EXISTS player (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	Name text NOT NULL,\n"
                + "	Daily date,\n"
                + "	Work text,\n"
                + "	Omnitrix text,\n"
                + " DiscordID long\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() {
        try {
            if (conn != null) {
                System.out.println("[SQLITE] O Banco de Dados foi desconectado!");
                conn.close();
                return;
            }

            System.out.println("[SQLITE] O Banco de Dados já está desconectado!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}