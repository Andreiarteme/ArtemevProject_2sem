import java.util.ArrayList;

public class QuoteContainer {
    private ArrayList<Quote> quotes;

    public QuoteContainer(){
        quotes = new ArrayList<Quote>();
    }

    public void add(Quote quote){
        quotes.add(quote);
    }

    public boolean quoteExists(String newQuote){
        for (Quote quote: quotes) {
            if (quote.getQuote().equals(newQuote)){
                return true;
            }
        }
        return false;
    }
    public void changeQuote(Quote quot){
        for (int i =0;i < quotes.size();i++){
            if (quotes.get(i).getId().equals(quot.getId())){
                quotes.set(i,quot);
                break;
            }
        }
    }

    public ArrayList<Quote> getArQuotes() {
        return quotes;
    }

}
