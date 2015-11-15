import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FilterBuilder {

	private String filter;
	
	public String makeFilter()
	{
		if(filter.isEmpty())
		{
			
		}
		
		
		return filter;
	}
	
	public String yearFilterPart()
	{
		DateFormat dateFormat = new SimpleDateFormat("yy");
		
		return (dateFormat.format(Calendar.getInstance().getTime()) + ")");
	}
	
}
