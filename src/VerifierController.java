import java.sql.SQLException;



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

    public User getUser() {
        return user;
    }

    public void insert() {
        InsertController insertController = new InsertController(dataBase, user);
        InsertView insertView = new InsertView(insertController);

    }

    public boolean delete(int ind) throws SQLException, ClassNotFoundException {
        String verGroup = dataBase.getVerifierGroups(user.getId());
        String userGroup = "";
        try {
            userGroup = dataBase.getUserGroup(dataBase.getQuotes().getArQuotes().get(ind).getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (userGroup.equals(verGroup) || user.getId().equals(dataBase.getQuotes().getArQuotes().get(ind).getUserId())) {
            dataBase.delete(ind);
            VerifierController verifierController = new VerifierController(dataBase, user);
            VerifierView verifierView = new VerifierView(verifierController);
            return true;
        }else{
            return false;
        }

    }

    public boolean edit(int ind) {
        String verGroup = dataBase.getVerifierGroups(user.getId());
        String userGroup = "";
        try {
            userGroup = dataBase.getUserGroup(dataBase.getQuotes().getArQuotes().get(ind).getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }


            if (userGroup.equals(verGroup) || user.getId().equals(dataBase.getQuotes().getArQuotes().get(ind).getUserId())) {
            Quote quote = dataBase.edit1(ind);
            EditController editController = new EditController(dataBase, user);
            EditView editView = new EditView(editController,quote);
                return true;
            }else{
                return false;
            }
    }


    public String getVerifierGroups() {
        return dataBase.getVerifierGroups(user.getId());
    }

    public String getUserGroup(String id) throws Exception {
        try {
            return dataBase.getUserGroup(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new Exception();
    }

    public void rename() {
        RenameController renameController = new RenameController(dataBase, user);
        RenameView renameView = new RenameView(renameController);
    }


}