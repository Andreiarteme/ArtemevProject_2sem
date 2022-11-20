import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class VerifierTableModel extends AbstractTableModel {
    private int columnCount = 5;
    private ArrayList<Quote> quotes;
    private ArrayList<String []> arrayList;
    private VerifierController verifierController;
    private int count = 0;

    public VerifierTableModel(ArrayList<Quote> quotes, VerifierController verifierController) throws Exception {
        this.quotes = quotes;
        this.verifierController = verifierController;
        arrayList = new ArrayList<String[]>();
        String verGroup = verifierController.getVerifierGroups();
        for (int i = 0;i < quotes.size();i++){
            String[] str = new String[columnCount];
            str[0] = quotes.get(i).getQuote();
            str[1] = quotes.get(i).getTeacher();
            str[2] = quotes.get(i).getSubject();
            str[3] = quotes.get(i).getData();
            str[4] = quotes.get(i).getUserId();

            String userGroup = verifierController.getUserGroup(quotes.get(i).getUserId());
            if (userGroup.equals(verGroup)) {
                str[4] = quotes.get(i).getUserId() + " Из моей группы";
            }
            if (quotes.get(i).getUserId().equals(verifierController.getUser().getId())) {
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
            case 4: return "ID автора";
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
        if (nameColumn.equals("ID автора")){
            return q.getData();
        }
        return null ;
    }
}