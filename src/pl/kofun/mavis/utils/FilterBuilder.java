package pl.kofun.mavis.utils;

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
	
	public String makeCurrentTimeFilter()
	{
		return makeFilter(Calendar.getInstance().get(Calendar.MONTH));
	}
	
	public String makeFilter(int currentMonth)
	{
		if(filter.isEmpty() || currentMonth != generatedForMonth)
		{
			generatedForMonth = currentMonth;
			filter = monthFilterPart(currentMonth) + yearFilterPart();
		}
		
		
		return filter;
	}
	
	public String makeFilter(int currentMonth, int currentYear)
	{
		filter = monthFilterPart(currentMonth) + yearFilterPart(currentYear);
		
		return filter;
	}
	
	public String monthFilterPart(int monthNumber)
	{
		DateFormatSymbols dateSymbols = new DateFormatSymbols(locale);
		
		return ( "(" + dateSymbols.getShortMonths()[monthNumber]);
	}
	
	public String yearFilterPart()
	{
		DateFormat dateFormat = new SimpleDateFormat("yy");
		
		return ( dateFormat.format(Calendar.getInstance().getTime()) + ")" );
	}
	
	public String yearFilterPart(int year)
	{
		DateFormat dateFormat = new SimpleDateFormat("yy");
		Calendar calendar = Calendar.getInstance();
		
		int yearOffset = year - calendar.get(Calendar.YEAR); 
		
		calendar.add(Calendar.YEAR, yearOffset);
		
		return ( dateFormat.format(calendar.getTime()) + ")");
	}
	
}
