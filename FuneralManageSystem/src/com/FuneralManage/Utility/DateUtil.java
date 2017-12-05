package com.FuneralManage.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	/**
	 * 获取日期的年和月
	 * @param date 日期
	 * @return 年月字符串
	 */
	public static String getYearAndMonth(String date)
	{
		String yearAndMonth = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 将字符串转换为日期
		Date dt;
		try {
			dt = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dt);
			int year = calendar.get(Calendar.YEAR);// 年
			int month = calendar.get(Calendar.MONTH) + 1;// 月
			yearAndMonth = month < 10 ? "" + year + "0" + month : "" + year + "" + month;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yearAndMonth;
	}

	/**
	 * 获取日期的年月日
	 * @param date 日期
	 * @return 年月日字符串
	 */
	public static String getYearAndMonthAndDay(String date)
	{
		String yearAndMonthAndDay = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 将字符串转换为日期
		Date dt = null;
		try
		{
			dt = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dt);
			int year = calendar.get(Calendar.YEAR);// 年
			int month = calendar.get(Calendar.MONTH) + 1;// 月
			int day = calendar.get(Calendar.DAY_OF_MONTH);// 日
			// 如果月份小于10
			if (month < 10)
			{
				if (day < 10)
				{
					yearAndMonthAndDay = "" + year + "0" + month + "0" + day;
				}
				else
				{
					yearAndMonthAndDay = "" + year + "0" + month + day;
				}
			}
			// 月份大于10
			else
			{
				if (day < 10)
				{
					yearAndMonthAndDay = "" + year + month + "0" + day;
				}
				else
				{
					yearAndMonthAndDay = "" + year + month + day;
				}
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return yearAndMonthAndDay;
	}
	
	public static String getYearAndMonthAndDay(Date date) {
		String yearAndMonthAndDay = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);// 年
		int month = calendar.get(Calendar.MONTH) + 1;// 月
		int day = calendar.get(Calendar.DAY_OF_MONTH);// 日
		// 如果月份小于10
		if (month < 10) {
			if (day < 10) {
				yearAndMonthAndDay = "" + year + "0" + month + "0" + day;
			} else {
				yearAndMonthAndDay = "" + year + "0" + month + day;
			}
		}
		// 月份大于10
		else {
			if (day < 10) {
				yearAndMonthAndDay = "" + year + month + "0" + day;
			} else {
				yearAndMonthAndDay = "" + year + month + day;
			}
		}
		return yearAndMonthAndDay;
	}
	
	public static Date addDateOneDay(Date date) {
		if (null == date) {
			return date;
		}
		Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.DATE, 1); //日期加1天
//     c.add(Calendar.DATE, -1); //日期减1天
        date = c.getTime();
        return date;
	}

}
