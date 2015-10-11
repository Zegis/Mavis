import java.util.LinkedList;
import java.lang.StringBuilder;

public class stringAdjuster {

	public static LinkedList<String> AdjustToBookList(LinkedList<String> listToAdjust)
	{
		LinkedList<String> ret = new LinkedList<>();
	
		StringBuilder lineForRet = new StringBuilder();
		
		while(listToAdjust.size() > 0)
		{
			lineForRet.append("<strong>");
			lineForRet.append(listToAdjust.removeFirst());
			lineForRet.append("</strong> - ");
			
			ret.add(lineForRet.toString());
			lineForRet.setLength(0);
		}
		
		return ret;
	}
}
