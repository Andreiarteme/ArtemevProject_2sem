import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class GuestTableModel extends AbstractTableModel {
    private int columnCount = 4;
    private ArrayList<Quote> quotes;
    private ArrayList<String []> arrayList;

    public GuestTableModel(ArrayList<Quote> quotes) {
        this.quotes = quotes;
        arrayList = new ArrayList<String[]>();
        for (int i = 0;i < quotes.size();i++){
            String[] str = new String[columnCount];
            str[0] = quotes.get(i).getQuote();
            str[1] = quotes.get(i).getTeacher();
            str[2] = quotes.get(i).getSubject();
            str[3] = quotes.get(i).getData();
            arrayList.add(str);
        }

    }

    public void addInTable(Quote quote){

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
            case 0: return "quote";
            case 1: return "teacher";
            case 2: return "subject";
            case 3: return "data";
        }
        return "";
    }

    public String getValueAt(int rowIndex, String nameColumn){
        Quote q = quotes.get(rowIndex);
        if (nameColumn.equals("quote")){
            return q.getQuote();
        }
        if (nameColumn.equals("teacher")){
            return q.getTeacher();
        }
        if (nameColumn.equals("subject")){
            return q.getSubject();
        }
        if (nameColumn.equals("data")){
            return q.getData();
        }
        return null ;
    }
}
