import java.sql.SQLException;

//public class VerifierController {
//}
//import java.sql.SQLException;

public class VerifierController {
    private DataBase dataBase;
    private User user;

    public VerifierController(DataBase dataBase, User user) {
        this.dataBase = dataBase;
        this.user = user;
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

    public void delete(int ind) throws SQLException, ClassNotFoundException {
        dataBase.delete(ind);
        DataBase dataBase = new DataBase();
        AdminController adminController = new AdminController(dataBase, user);
        AdminView adminView = new AdminView(adminController);


    }

    public void edit(int ind) {
        Quote quote = dataBase.edit1(ind);
        EditController editController = new EditController(dataBase, user);
        EditView editView = new EditView(editController,quote);
    }

    public boolean whatgroup() {
//        String group = dataBase.
        return false;
    }
}