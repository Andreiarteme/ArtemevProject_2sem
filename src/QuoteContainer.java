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
                return false;
            }
        }
        return true;
    }

    public ArrayList<Quote> getArQuotes() {
        return quotes;
    }
}
