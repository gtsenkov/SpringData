package DATABASEACCESSWITHJDBClab.jdbcdemo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String DB_URL = "jdbc:mysql://localhost:3306/soft_uni";
    public static String SQL_QUERY = " SELECT * FROM employees WHERE salary > ?";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter DB user name (<Enter> for 'root'): ");
        String userName = scan.nextLine().trim();
        userName = userName.length() > 0 ? userName : "root";

        System.out.println("Enter DB password (<Enter> for 'root'): ");
        String password = scan.nextLine().trim();
        password = password.length() > 0 ? password : "0899757644";

        // 1. Load DB Driver
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.printf("Database driver '%s%n' not found", DB_DRIVER);
            System.exit(0);
        }

        System.out.println("DB Driver load successfully");

        // 2. Query params
        System.out.println("Enter minimal salary (<Enter> for '40000'): ");
        String salaryStr = scan.nextLine().trim();
        salaryStr = salaryStr.length() > 0 ? salaryStr : "40000";
        double salary = 40000;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException ex) {
            System.err.printf("Invalid number: '%s'", salaryStr);
        }

        // 3. Connect to DB
        Properties props = new Properties();
        props.setProperty("user", userName);
        props.setProperty("password", password);


        try (Connection con = DriverManager.getConnection(DB_URL, props)){
             System.out.printf("DB connection created successfully: %s%n", DB_URL);

            // 4. Create prepared statement
            PreparedStatement ps =
                    con.prepareStatement(SQL_QUERY);
            // 5. Execute prepared statement with parameter
            ps.setDouble(1, salary);
            ResultSet resultSet = ps.executeQuery();

            // 6. Print results

            while (resultSet.next()) {
                System.out.printf("| %10d | %-15.15s | %-15.15s | %10.2f |%n",
                        resultSet.getLong("employee_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDouble("salary")
                );
            }

        } catch (SQLException throwables) {
            System.err.printf("Error closing Db connection %s",
                    throwables.getMessage());
        }
    }
}
