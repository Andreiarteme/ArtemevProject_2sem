import java.sql.*;


public class DataBase {
    public Connection connection;
    public UserContainer users;

    public DataBase() {
        users = makeUsers();

    }

    public UserContainer getUsers() {
        return users;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1965_staff",
                    "std_1965_staff", "qwerty321");
//            Statement statement = connection.createStatement();
//            connection.close();

        return connection;
    }

    public void registrUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO users (username, password)" +
                "VALUES (?,?)";
        try {
            PreparedStatement prStatement = getConnection().prepareStatement(insert);
            prStatement.setString(1, user.getLogin());
            prStatement.setString(2, user.getHashPassword());
            prStatement.executeUpdate();
        }catch (Exception e){

        }

        String uid = getNewUserId(user.getLogin());

//        String sel = "SELECT users.id FROM users WHERE users.username = '" + user.getLogin() + "'";
//        PreparedStatement prStatement = getConnection().prepareStatement(sel);
//        ResultSet res = prStatement.executeQuery();
//        String uid = res.getString("id");

        insToUser_role(uid);

//        String ins = "INSERT INTO user_role (user_id, role_id)" +
//                "VALUES (?,?)";
//        PreparedStatement prSt = getConnection().prepareStatement(ins);
//        prSt.setString(1, uid);
//        prSt.setString(2, "2");
//        prSt.executeUpdate();
        //connection.close();

    }
    public String getNewUserId(String usLog) throws SQLException, ClassNotFoundException {
        String sel = "SELECT id FROM users WHERE users.username = '" + usLog + "'";
        String uid = "7";
        try {
            PreparedStatement prStat = getConnection().prepareStatement(sel);
            ResultSet res = prStat.executeQuery();
            while(res.next()){
                uid = res.getString("id");
               // System.out.println("!!!!ID!!!!!" + uid);
            }
        }catch (Exception e){

        }
        return uid;
    }
    public void insToUser_role(String usId) throws SQLException, ClassNotFoundException {
        String ins = "INSERT INTO user_role (user_id, role_id)" +
                "VALUES (?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(ins);
            prSt.setString(1, usId);
            prSt.setString(2, "2");
            prSt.executeUpdate();
            //System.out.println("YYYYEEESSS");
        }catch (Exception e){

        }

    }

    public UserContainer makeUsers(){
        //ResultSet result;
        //String select = "SELECT * FROM users" ;
        String select1 = "SELECT users.username,users.password,user_role.role_id FROM users,user_role WHERE users.id = user_role.user_id";
        try {
            PreparedStatement prStatement = getConnection().prepareStatement(select1);
//            prStatement.setString(1, user.getLogin());
            ResultSet result = prStatement.executeQuery();
           users = new UserContainer();
            while(result.next()){
                String un = result.getString("username");
                String up = result.getString("password");
                String ur = result.getString("role_id");
                users.add(new User(result.getString("username"),result.getString("password"),result.getString("role_id")));
                System.out.println(un + " === " + up);
            }
            connection.close();
        }catch (Exception e){

        }
        return users;
    }

        public String checkLoginAndPassword(String loginText, String toHash) {
        String str = users.check(loginText,toHash);
            System.out.println(str + "database");
        return str;
       // return users.check(loginText,toHash);//сделать чтоб возвращал роль
    }

    public void checkRole(String log, String role) {


    }
}