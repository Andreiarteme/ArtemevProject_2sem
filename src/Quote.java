public class Quote {
//    private String id;
    private String quote;
    private String teacher;
    private String subject;
    private String data;
    private String userId;
    private String id;


    public Quote(String id, String quote, String teacher, String subject, String data, String userId) {
        this.id = id;
        this.quote = quote;
        this.teacher = teacher;
        this.subject = subject;
        this.data = data;
        this.userId = userId;
    }

//    public String getId() {
//        return id;
//    }
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getId(){
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
