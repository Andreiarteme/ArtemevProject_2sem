import javax.swing.*;
import java.sql.SQLException;




public class UserController {
    private DataBase dataBase;
    private User user;

    public UserController(DataBase dataBase, User user) {
        this.dataBase = dataBase;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public DataBase getDataBase() {
        return dataBase;
    }


    public void toLoginWindow() {
        LoginController loginController = new LoginController(dataBase);
        LoginView loginView = new LoginView(loginController);

    }

    public void insert() {
        InsertController insertController = new InsertController(dataBase, user);
        InsertView insertView = new InsertView(insertController);

    }

    public boolean delete(int ind) throws SQLException, ClassNotFoundException {
        if (dataBase.checkAuthor(ind, user)){
        dataBase.delete(ind);
//        DataBase dataBase = new DataBase();

        UserController userController = new UserController(dataBase, user);
        UserView userView = new UserView(userController);
            return true;
        }else{
            return false;
        }

    }

    public boolean edit(int ind) {
            if (dataBase.checkAuthor(ind, user)) {
                Quote quote = dataBase.edit1(ind);
                EditController editController = new EditController(dataBase, user);
                EditView editView = new EditView(editController, quote);
                return true;
            }else{
                return false;
            }
        }
//    public String getUserId(User user) throws SQLException, ClassNotFoundException {
//        return dataBase.getUserId(user.getLogin());
//    }
}