package pl.kofun.mavis.counters;

public class PeriodToCount {
	public int Year;
	public int Month;
	public String Filter;
	
	public PeriodToCount(int year, int month, String filter)
	{
		this.Year = year;
		this.Month = month;
		this.Filter = filter;
	}
	
	public PeriodToCount()
	{
		this.Year = 0;
		this.Month = 0;
		this.Filter = "";
	}
}
