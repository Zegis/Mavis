import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FilterBuilder {

	private String filter;
	private Locale locale;
	private int generatedForMonth;
	
	public FilterBuilder()
	{
		generatedForMonth = 0;
		filter = new String();
		locale = new Locale("pl");
	}
	
	public String makeFilter(int currentMonth)
	{
		if(filter.isEmpty() || currentMonth != generatedForMonth)
		{
			System.out.println("Generuje filtr dla " + currentMonth);
			generatedForMonth = currentMonth;
			filter = monthFilterPart(currentMonth) + yearFilterPart();
		}
		
		
		return filter;
	}
	
	public String monthFilterPart(int monthNumber)
	{
		DateFormatSymbols dateSymbols = new DateFormatSymbols(locale);
		
		return ( "(" + dateSymbols.getShortMonths()[monthNumber -1] ); //in monthNumber 1 is for febuary and in array 0 is for febuary 
	}
	
	public String yearFilterPart()
	{
		DateFormat dateFormat = new SimpleDateFormat("yy");
		
		return ( dateFormat.format(Calendar.getInstance().getTime()) + ")" );
	}
	
}
