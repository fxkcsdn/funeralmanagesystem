package com.FuneralManage.Utility;

import java.util.Calendar;

public class DateUtil {
	/**
	 * 获取当前年月；
	 * @return
	 */
	public static String getCurrentYearAndMounth()
	{
		Calendar calendar = Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int mounth=calendar.get(Calendar.MONTH)+1;
		if(mounth<10)
			return ""+year+"0"+mounth;
		else
			return ""+year+""+mounth;
	}
	/**
	 * 获取当前年月日
	 * @return
	 */
	public static String getCurrentYearAndMounthAndDay()
	{
		Calendar calendar = Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int mounth=calendar.get(Calendar.MONTH)+1;
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		if(mounth<10)
			if(day<10)
				return ""+year+"0"+mounth+"0"+day;
			else
				return ""+year+"0"+mounth+day;
		else
			if(day<10)
				return ""+year+""+mounth+"0"+day;
			else
				return ""+year+""+mounth+day;
	}
	/**
	 * 获取当前月日
	 * @return
	 */
	public static String getCurrentMounthAndDay()
	{
		Calendar calendar = Calendar.getInstance();
		int mounth=calendar.get(Calendar.MONTH)+1;
		int day=calendar.get(Calendar.DAY_OF_MONTH);
		if(mounth<10)
			if(day<10)
				return "0"+mounth+"0"+day;
			else
				return "0"+mounth+day;
		else
			if(day<10)
				return ""+mounth+"0"+day;
			else
				return ""+mounth+day;
	}
	/**
	 * 获取当前年；
	 * @return
	 */
	public static String getCurrentYear()
	{
		Calendar calendar = Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		return ""+year;
	}

}
