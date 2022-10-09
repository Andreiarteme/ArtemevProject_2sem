import javax.security.auth.login.LoginException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws LoginException, NoSuchAlgorithmException {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1965_staff",
//                    "std_1965_staff", "qwerty321");
//            Statement statement = connection.createStatement();
//            String query = "SELECT * FROM posts";
//            ResultSet result = statement.executeQuery(query);
//           convertor("hello world");
//
//            while(result.next()){
//                int id = result.getInt("id");
//                String login = result.getString("login");
//                //String short_name = result.getString("short_name");
//                System.out.print("Vacant post: ");
//                System.out.print("id = " + id);
//                System.out.print(", login = \"" + login + "\"\n");
//                //System.out.println(", short name = \"" + short_name + "\".");
//            }
//            connection.close();
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }



      //  toHash("hello world");
        DataBase dataBase = new DataBase();
        //LoginModel loginModel = new LoginModel();
        LoginController  loginController = new LoginController(dataBase);
        LoginView loginView = new LoginView(loginController);
    }

}