package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class,Car.class);

       User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(new Car("BMW",3));
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(new Car("MERCEDES",5));
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(new Car("TESLA",7));
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(new Car("TOYOTA",9));
      userService.addUser(user1);
      userService.addUser(user2);
      userService.addUser(user3);
      userService.addUser(user4);

      for (User user : userService.getUsersList()) {
         System.out.println(user + " " + user.getCar());
      }

      User userOut = userService.getUserByCarModelAndSeries("TOYOTA",9);
      System.out.println(userOut.toString());

      List<User> users = userService.getUsersList();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      userService.getUserByCarModelAndSeries("TOYOTA",9);

      context.close();
   }
}
