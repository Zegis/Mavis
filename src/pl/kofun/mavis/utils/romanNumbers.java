package pl.kofun.mavis.utils;

public class romanNumbers {

	public static String intToRoman(int number)
	{
		StringBuilder ret = new StringBuilder();
		
		int times = 0;
		String [] romans = new String[]{"I","IV","V","IX","X","XL","L","XC","C","CD","D","DM","M"};
		int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
		
		for (int i= ints.length-1; i >=0; --i)
		{
			times = number / ints[i];
			number %= ints[i];
			
			while(times > 0)
			{
				ret.append(romans[i]);
				--times;
			}			
		}		
		
		return ret.toString();
	}
	
}
