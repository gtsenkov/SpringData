import entity.User;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/fsd";
    private static final String USER = "root";
    private static final String PASSWORD = "0899757644";

    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {


        Connection connection = getConnection();

        EntityManager<User> entityManager = new EntityManager<>(connection);

        System.out.println("Connected to database.");
        User user = new User();
  //  user.setId(8);
        user.setUsername("8");
        user.setPassword("@@@@@@@@");
        user.setAge(88);
        user.setRegistrationDate(LocalDate.of(2000,11,1));

        entityManager.persist(user);


       System.out.println(entityManager.findFirst(User.class, " where username like '%8'"));
      // System.out.println(entityManager.findById(User.class, 7));
  // System.out.println(entityManager.delete(User.class, 16));

    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_STRING, "root", "0899757644");
    }
}

