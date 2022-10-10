import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class InsertController {

    private DataBase dataBase;
//    private LoginModel loginModel;

    public InsertController(/*LoginModel loginModel*/ DataBase dataBase) {
//        this.loginModel = loginModel;
        this.dataBase = dataBase;
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
        DataBase dataBase = new DataBase();
        System.out.println("regQuote from InsertController");
        if (dataBase.getQuotes().quoteExists(quore)){
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
        DataBase dataBase = new DataBase();
        AdminController adminController = new AdminController(dataBase);
        AdminView adminView = new AdminView(adminController);
    }
}
