import java.security.MessageDigest;
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
            ResultSet result = prStatement.executeQuery();
            quotes = new QuoteContainer();
            while(result.next()){
                quotes.add(new Quote(result.getString("id"),result.getString("quote"), result.getString("teacher"), result.getString("subject"), result.getString("data"), result.getString("user_id")));
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
        return connection;
    }

    public void registrUser(User user){
        String insert = "INSERT INTO `users` (`username`, `password`, `group`) VALUES (?, ?, ?);";
        try {
            PreparedStatement prStatement = getConnection().prepareStatement(insert);
            prStatement.setString(1, user.getLogin());
            prStatement.setString(2, user.getHashPassword());
            prStatement.setString(3, user.getGroup());
            prStatement.executeUpdate();

            connection.close();
            users.add(user);
            insToUser_role(user);
        }catch (Exception e){

        }
    }


    public String getUserId(String login) throws SQLException, ClassNotFoundException {
        String sel = "SELECT users.id FROM users WHERE users.username = '" + login + "'";
        String id = "";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(sel);
            ResultSet result = prSt.executeQuery();
            while(result.next()){
                id = result.getString("id");
            }
            connection.close();
        }catch (Exception e){

        }
        return id;
    }

    public String getNewUserId(String login) throws SQLException, ClassNotFoundException {
        String sel =" SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'std_1965_staff' AND   TABLE_NAME   = 'users';";
        String autoIncrement = "";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(sel);
            ResultSet result = prSt.executeQuery();
            while(result.next()){
                String s = result.getString("AUTO_INCREMENT");
                autoIncrement = result.getString("AUTO_INCREMENT");
            }
            connection.close();
        }catch (Exception e){
        }
        return autoIncrement;
    }

    public void insToUser_role(User user) throws SQLException, ClassNotFoundException {
        String newId = getUserId(user.getLogin());
        String insert = "INSERT INTO user_role (user_id, role_id) VALUES (?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(insert);
            prSt.setString(1, newId);
            prSt.setString(2, "2");
            prSt.executeUpdate();
            connection.close();
        }catch (Exception e){

        }

    }

    public UserContainer makeUsers(){
        String select1 = "SELECT users.username,users.password,user_role.role_id, users.group FROM users,user_role WHERE users.id = user_role.user_id";
        try {
            PreparedStatement prStatement = getConnection().prepareStatement(select1);
            ResultSet result = prStatement.executeQuery();
           users = new UserContainer();
            while(result.next()){
                String username = result.getString("username");
                String userId = getUserId(username);
                users.add(new User(userId, username,result.getString("password"),result.getString("role_id"), result.getString("group")));
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
    }

    public void registrQuote(Quote quote) {
        String insert = "INSERT INTO quotes (quote, teacher, subject, data) VALUES ( ?, ?, ?, ?);";
        try {
            PreparedStatement prStatement = getConnection().prepareStatement(insert);
            prStatement.setString(1, quote.getQuote());
            prStatement.setString(2, quote.getTeacher());
            prStatement.setString(3, quote.getSubject());
            prStatement.setString(4, quote.getData());
            prStatement.executeUpdate();
            String rr = getNewQuoteId(quote.getQuote());
            insToQuote_role(rr, quote);
            //добавление в контейнер
            quote.setId(rr);
            quotes.add(quote);
            connection.close();
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
            }
            connection.close();
        }catch (Exception e){

        }
        return qid;
    }
    public void insToQuote_role(String qid, Quote quote) throws SQLException, ClassNotFoundException {
        String ins = "INSERT INTO user_quote (user_id, quote_id)" +
                "VALUES (?,?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(ins);
            prSt.setString(1, quote.getUserId());
            prSt.setString(2, qid);
            prSt.executeUpdate();
            connection.close();
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
            connection.close();
            quotes.getArQuotes().remove(ind);
        }catch (Exception e){

        }


    }

    public Quote edit1(int ind) {
        Quote quot = quotes.getArQuotes().get(ind);
        return quot;
    }
    public boolean checkAuthor(int ind, User user) {
        if (quotes.getArQuotes().get(ind).getUserId().equals(user.getId())){
            return true;
        }
        return false;
    }

    public void edit2Quote(Quote quote) {
        String i = quote.getId();
        String q = quote.getQuote();
        String t = quote.getTeacher();
        String s = quote.getSubject();
        String d = quote.getData();


        try {
            String ins =  "UPDATE quotes SET quote = '"+ q +"', teacher = '"+ t +"', subject = '"+ s +"', data = '"+ d +"' WHERE quotes.id = '"+ i +"'";
            PreparedStatement prSt = getConnection().prepareStatement(ins);

            prSt.executeUpdate();
            connection.close();
            quotes.changeQuote(quote);
        }catch (Exception e){

        }

    }

    public User getUser(String login) throws Exception {
        User user;
        user = users.getUser(login);
        return user;
    }

    public String getVerifierGroups(String id) {
        String sel = "SELECT `group_number` FROM verifier_group WHERE `verifier_id` = " + id;
        String qid = "bad";
        try {
            PreparedStatement prStat = getConnection().prepareStatement(sel);
            ResultSet res = prStat.executeQuery();
            while(res.next()){
                qid = res.getString("group_number");
            }
            connection.close();
        }catch (Exception e){

        }
        return qid;
    }

    public String getUserGroup(String id) throws Exception {
        String userGroup;
        for (int i = 0;i < users.getUsers().size();i++) {
            if (users.getUsers().get(i).getId().equals(id)){
                userGroup = users.getUsers().get(i).getGroup();
                return userGroup;
            }
        }
        throw new Exception ("ошибка в DataBase getUserGroup()");
    }

    public void rename(String id, String login, String password, String group) {
        try {   MessageDigest md5 = MessageDigest.getInstance("MD5");
                byte[] bytes = md5.digest(password.getBytes());
                StringBuilder builder = new StringBuilder();
                for (byte b : bytes)
                    builder.append(String.format("%02X ", b));
                String hash = builder.toString().replaceAll(" ","").toLowerCase();

            String ins = "UPDATE users SET `username` = '" + login +  "', `password`= '" + hash + "',  `group` = '" + group + "' WHERE users.id = '" + id + "'";
            PreparedStatement prSt = getConnection().prepareStatement(ins);
            prSt.executeUpdate();
            connection.close();
            //метод для замены в UserContainer
            for (int i = 0;i < users.getUsers().size();i++) {
                if (users.getUsers().get(i).getId().equals(id)){
                    users.getUsers().get(i).setLogin(login);
                    users.getUsers().get(i).setHashPassword(hash);
                    users.getUsers().get(i).setGroup(group);
                }
            }
        }catch (Exception e){
        }
    }
}
