import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class UserTableModel extends AbstractTableModel {
    private int columnCount = 5;
    private ArrayList<Quote> quotes;
    private ArrayList<String []> arrayList;
    private User user;
    private int count = 0;

    public UserTableModel(ArrayList<Quote> quotes, User user) {
        this.quotes = quotes;
        this.user = user;
        arrayList = new ArrayList<String[]>();
        for (int i = 0;i < quotes.size();i++){
            String[] str = new String[columnCount];
            str[0] = quotes.get(i).getQuote();
            str[1] = quotes.get(i).getTeacher();
            str[2] = quotes.get(i).getSubject();
            str[3] = quotes.get(i).getData();
            str[4] = "Не моя запись";
            if (quotes.get(i).getUserId().equals(user.getId())) {
                String c = Integer.toString(++count);
                str[4] = quotes.get(i).getUserId() + ": Моя запись №" + c;
            }
            arrayList.add(str);
        }

    }

    public void addInTable(Quote quote){

    }

    public int getCount() {
        return count;
    }

    @Override
    public int getRowCount() {
        return quotes.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] str = arrayList.get(rowIndex);
        return str[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0: return "Цитата";
            case 1: return "Преподаватель";
            case 2: return "Предмет";
            case 3: return "Дата";
            case 4: return "Чья запись";
        }
        return "";
    }

    public String getValueAt(int rowIndex, String nameColumn){
        Quote q = quotes.get(rowIndex);
        if (nameColumn.equals("Цитата")){
            return q.getQuote();
        }
        if (nameColumn.equals("Преподаватель")){
            return q.getTeacher();
        }
        if (nameColumn.equals("Предмет")){
            return q.getSubject();
        }
        if (nameColumn.equals("Дата")){
            return q.getData();
        }
        if (nameColumn.equals("Чья запись")){
            return q.getData();
        }
        return null ;
    }
}
