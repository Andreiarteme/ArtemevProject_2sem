import java.sql.*;


public class DataBase {
    public Connection connection;
    public UserContainer users;
    public QuoteContainer quotes;

    public DataBase() {
        users = makeUsers();
        quotes = makeQuote();
    }

    private QuoteContainer makeQuote() {
        String select1 ="SELECT quotes.quote, quotes.teacher, quotes.subject, quotes.data, user_quote.user_id FROM quotes,user_quote WHERE quotes.id = user_quote.quote_id";
        try {
            PreparedStatement prStatement = getConnection().prepareStatement(select1);
//            prStatement.setString(1, user.getLogin());
            ResultSet result = prStatement.executeQuery();
            quotes = new QuoteContainer();
            while(result.next()){
//                String un = result.getString("quote");
//                String up = result.getString("teacher");
//                String ur = result.getString("subject");
//                String un1 = result.getString("data");
//                String up1 = result.getString("user_id");
                quotes.add(new Quote(result.getString("quote"), result.getString("teacher"), result.getString("subject"), result.getString("data"), result.getString("user_id")));
//                System.out.println(un+up+ur+un1+up1);
            }
            connection.close();
        }catch (Exception e){

        }
        return quotes;
    }

    public QuoteContainer getQuotes() {
        return quotes;
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
//                String un = result.getString("username");
//                String up = result.getString("password");
//                String ur = result.getString("role_id");
                users.add(new User(result.getString("username"),result.getString("password"),result.getString("role_id")));
//                System.out.println(un + " === " + up);
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

    public void registrQuote(Quote quote) {
        String insert = "INSERT INTO quotes (quote, teacher, subject, data) VALUES ( ?, ?, ?, ?);";
        try {
            PreparedStatement prStatement = getConnection().prepareStatement(insert);
            prStatement.setString(1, quote.getQuote());
            prStatement.setString(2, quote.getTeacher());
            prStatement.setString(3, quote.getSubject());
            prStatement.setString(4, quote.getData());
//            prStatement.setString(5, quote.getUserId());
            prStatement.executeUpdate();
            System.out.println("Мы здесь");
            String rr = getNewQuoteId(quote.getQuote());
            insToQuote_role(rr);


        }catch (Exception e){

        }
    }
    public String getNewQuoteId(String quote) throws SQLException, ClassNotFoundException {
        String sel = "SELECT id FROM quotes WHERE quotes.quote = '" + quote + "'";
        String qid = "1";
        try {
            PreparedStatement prStat = getConnection().prepareStatement(sel);
            ResultSet res = prStat.executeQuery();
            while(res.next()){
                qid = res.getString("id");
                 System.out.println("!!!!ID!!!!!" + qid);
            }
        }catch (Exception e){

        }
        return qid;
    }
    public void insToQuote_role(String qid) throws SQLException, ClassNotFoundException {
        String ins = "INSERT INTO user_quote (user_id, quote_id)" +
                "VALUES (?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(ins);
            prSt.setString(1, "5");
            prSt.setString(2, qid);
            prSt.executeUpdate();
            System.out.println("YYYYEEESSS");
        }catch (Exception e){

        }

    }

    public void delete(int ind) throws SQLException, ClassNotFoundException {
        String quot = quotes.getArQuotes().get(ind).getQuote();
        String ins =  "DELETE FROM quotes WHERE quotes.quote = ?";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(ins);
            prSt.setString(1, quot);
            prSt.executeUpdate();
            System.out.println("YYYYEEESSS");
        }catch (Exception e){

        }


    }
}
