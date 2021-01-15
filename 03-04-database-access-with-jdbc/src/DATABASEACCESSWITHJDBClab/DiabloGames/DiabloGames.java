package DATABASEACCESSWITHJDBClab.DiabloGames;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DiabloGames {
    public static void main(String[] args) {
        //1. Read props from external property file

        Properties props = new Properties();
        Class<DiabloGames> diabloGamesClass = DiabloGames.class;
        String path = diabloGamesClass.getClassLoader()
                .getResource("DATABASEACCESSWITHJDBClab/DiabloGames/jdbc.properties").getPath();
        System.out.printf("Resource path: %s%n", path);

        try {
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO: add meaningful defaults

        System.out.println(props);
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter DB user name (<Enter> for 'Alex'): ");
        String userName = scan.nextLine().trim();
        userName = userName.length() > 0 ? userName : "Alex";

        // 2. Try with resources - Connection, PreparedStatement

        try (Connection con = DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password"));
             PreparedStatement ps = con.prepareStatement(
                     "SELECT u.id, first_name, last_name, COUNT(ug.game_id) AS count " +
                             "FROM users u JOIN users_games ug ON u.id = ug.user_id " +
                             "WHERE user_name = ?"
             )) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            // 3. Print results
            while (rs.next()) {
                if (rs.getLong("id") == 0) {
                    System.out.printf("DB user with user name '%s' not found.", userName);
                } else {
                    System.out.printf("| %10d | %-15.15s | %-15.15s | %10d |%n",
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("count")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
