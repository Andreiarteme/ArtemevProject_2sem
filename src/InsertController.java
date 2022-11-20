import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class InsertController {

    private DataBase dataBase;
    private User user;


    public User getUser() {
        return user;
    }

    public InsertController(DataBase dataBase, User user) {
        this.dataBase = dataBase;
        this.user = user;
    }

    public boolean registration(String quore, String teacher, String subject,String data, String userId,JLabel info) throws LoginException, NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        if (!quore.equals("")){
            return regQuote(quore,teacher, subject, data, userId, info);

        }else{
            info.setText("Заполните поля!");
            return false;
        }


    }

    private boolean regQuote(String quore, String teacher, String subject,String data,String userId, JLabel info) throws SQLException, ClassNotFoundException {
        System.out.println("regQuote from InsertController");
        if (!dataBase.getQuotes().quoteExists(quore)){
            Quote quote = new Quote("",quore,  teacher,  subject,  data,  userId);//по умолчанию роль user
            dataBase.registrQuote(quote);
            info.setText("Запись успешно добавленна!");
            goBack();
            return true;
        }else{
            info.setText("Данная цитата уже существует!");
            return false;
        }

    }


    public void goBack() {
        switch (Integer.parseInt(user.getRole())){
            case 1:
                AdminController adminController = new AdminController(dataBase, user);
                AdminView adminView = new AdminView(adminController);
                break;
            case 2:
                UserController userController = new UserController(dataBase, user);
                UserView userView = new UserView(userController);
                break;
            case 3:
                VerifierController verifierontroller = new VerifierController(dataBase, user);
                VerifierView verifierView = new VerifierView(verifierontroller);
                break;
        }
    }
}
