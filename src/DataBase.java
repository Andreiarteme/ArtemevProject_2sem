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
        String select1 ="SELECT quotes.id, quotes.quote, quotes.teacher, quotes.subject, quotes.data, user_quote.user_id FROM quotes,user_quote WHERE quotes.id = user_quote.quote_id";
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
                quotes.add(new Quote(result.getString("id"),result.getString("quote"), result.getString("teacher"), result.getString("subject"), result.getString("data"), result.getString("user_id")));
//                System.out.println(un+up+ur+un1+up1);
            }
            connection.close();
            System.out.println("my connectoion closed!!!");
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
        System.out.println("The connection on the my method");

        return connection;
    }

    //надо изменить, добавить группу юзера
    public void registrUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO `users` (`username`, `password`, `group`) VALUES (?, ?, ?);";
        try {
            PreparedStatement prStatement = getConnection().prepareStatement(insert);
            prStatement.setString(1, user.getLogin());
            prStatement.setString(2, user.getHashPassword());
            prStatement.setString(3, user.getGroup());
            prStatement.executeUpdate();
            System.out.println("DataBase registration");

            connection.close();
            users.add(user);
            insToUser_role(user);
            System.out.println("my connectoion closed!!!");
        }catch (Exception e){

        }

//        String uid = getNewUserId(user.getLogin());

//        String sel = "SELECT users.id FROM users WHERE users.username = '" + user.getLogin() + "'";
//        PreparedStatement prStatement = getConnection().prepareStatement(sel);
//        ResultSet res = prStatement.executeQuery();
//        String uid = res.getString("id");
//        String ins = "INSERT INTO user_role (user_id, role_id)" +
//                "VALUES (?,?)";
//        PreparedStatement prSt = getConnection().prepareStatement(ins);
//        prSt.setString(1, uid);
//        prSt.setString(2, "2");
//        prSt.executeUpdate();
        //connection.close();

    }


    public String getUserId(String login) throws SQLException, ClassNotFoundException {
        String sel = "SELECT users.id FROM users WHERE users.username = '" + login + "'";
        String autoIncrement = "";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(sel);
            ResultSet result = prSt.executeQuery();
            while(result.next()){
//                String s = result.getString("AUTO_INCREMENT");
                autoIncrement = result.getString("id");
//                int newId = Integer.getInteger(autoIncrement)-1;
//                autoIncrement = Integer.toString(newId);
                System.out.println("!!!!AUTO_INCREMENT!!!!!" + autoIncrement);
            }
            connection.close();
            System.out.println("my connectoion closed!!!");
        }catch (Exception e){

        }
        return autoIncrement;
    }

   //надо сделать чтлобы возвращал не 7 а то что надо
    public String getNewUserId(String login) throws SQLException, ClassNotFoundException {
        String sel =" SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'std_1965_staff' AND   TABLE_NAME   = 'users';";
        String autoIncrement = "";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(sel);
            ResultSet result = prSt.executeQuery();
            while(result.next()){
                String s = result.getString("AUTO_INCREMENT");
                autoIncrement = result.getString("AUTO_INCREMENT");
                int newId = Integer.getInteger(autoIncrement)-1;
                autoIncrement = Integer.toString(newId);
                System.out.println("!!!!AUTO_INCREMENT!!!!!" + newId);
            }
            connection.close();
            System.out.println("my connectoion closed!!!");
        }catch (Exception e){

        }
        return autoIncrement;
    }
    public void insToUser_role(User user) throws SQLException, ClassNotFoundException {
//        String newId = getNewUserId(user.getLogin());
        String newId = getUserId(user.getLogin());
        String insert = "INSERT INTO user_role (user_id, role_id) VALUES (?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(insert);
            prSt.setString(1, newId);
            prSt.setString(2, "2");
            prSt.executeUpdate();
            System.out.println("YYYYEEESSS я добавил в роль");
            connection.close();
            System.out.println("my connectoion closed!!!");
        }catch (Exception e){

        }

    }

    public UserContainer makeUsers(){
        //ResultSet result;
        //String select = "SELECT * FROM users" ;
        String select1 = "SELECT users.username,users.password,user_role.role_id, users.group FROM users,user_role WHERE users.id = user_role.user_id";
        try {
            PreparedStatement prStatement = getConnection().prepareStatement(select1);
//            prStatement.setString(1, user.getLogin());
            ResultSet result = prStatement.executeQuery();
           users = new UserContainer();
            while(result.next()){
//                String un = result.getString("username");
//                String up = result.getString("password");
//                String ur = result.getString("role_id");
                users.add(new User(result.getString("username"),result.getString("password"),result.getString("role_id"), result.getString("group")));
//                System.out.println(un + " === " + up);
            }
            connection.close();
        }catch (Exception e){

        }
        return users;
    }

        public boolean checkLoginAndPassword(String loginText, String toHash) {
        boolean str = users.check(loginText,toHash);
            System.out.println(str + "database");
        return str;
       // return users.check(loginText,toHash);//сделать чтоб возвращал роль
    }

//    public String getUserId(String loginText) {
//        String sel = "SELECT id FROM user WHERE quotes.quote = '" + loginText + "'";
//        String qid = "1";
//        try {
//            PreparedStatement prStat = getConnection().prepareStatement(sel);
//            ResultSet res = prStat.executeQuery();
//            while(res.next()){
//                qid = res.getString("id");
//                System.out.println("!!!!ID!!!!!" + qid);
//            }
//        }catch (Exception e){
//
//        }
//        return qid;
//    }

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
            connection.close();
            System.out.println("my connectoion closed!!!");

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
            connection.close();
            System.out.println("my connectoion closed!!!");
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
            connection.close();
            System.out.println("my connectoion closed!!!");
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
            connection.close();
            System.out.println("my connectoion closed!!!");
        }catch (Exception e){

        }


    }

    public Quote edit1(int ind) {
        Quote quot = quotes.getArQuotes().get(ind);
        return quot;
    }

    public void edit2Quote(Quote quote) {
        //String id = quote.getId();
        String q = quote.getQuote();
        String t = quote.getTeacher();
        String s = quote.getSubject();
        String d = quote.getData();
        String i = quote.getId();

//        String ins =  "UPDATE quotes SET quote = '?', teacher = '?', subject = '?', data = '?' WHERE quotes.id = '?'";
        System.out.println("БД вот edit2Qotes");
        try {
            String ins =  "UPDATE quotes SET quote = '"+ q +"', teacher = '"+ t +"', subject = '"+ s +"', data = '"+ d +"' WHERE quotes.id = '"+ i +"'";
            PreparedStatement prSt = getConnection().prepareStatement(ins);
//            prSt.setString(1, quote.getQuote());
//            prSt.setString(2, quote.getTeacher());
//            prSt.setString(3, quote.getSubject());
//            prSt.setString(4, quote.getData());
//            prSt.setString(5, quote.getId());
            prSt.executeUpdate();
            System.out.println("YYYYEEESSS");
            connection.close();
            System.out.println("my connectoion closed!!!");
        }catch (Exception e){

        }

    }

    public User getUser(String login) throws Exception {
        User user;
        user = users.getUser(login);
        return user;
    }
}
