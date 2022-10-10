import java.sql.SQLException;

public class AdminController {
    private DataBase dataBase;

    public AdminController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public void toLoginWindow() {
        LoginController loginController = new LoginController(dataBase);
        LoginView loginView = new LoginView(loginController);

    }

    public void insert() {
        InsertController insertController = new InsertController(dataBase);
        InsertView insertView = new InsertView(insertController);

    }

    public void delete(int ind) throws SQLException, ClassNotFoundException {
        dataBase.delete(ind);
        DataBase dataBase = new DataBase();
        AdminController adminController = new AdminController(dataBase);
        AdminView adminView = new AdminView(adminController);


    }
}