/* Copyright reserved
 * 一个符合国人习惯的、好用的MyDate类
 * v1 2020-4-19
 * @author StarSky/李辰剑@pku
 * 
 * -有各类常用构造方法，setter getter
 *  以及addDay,addMonth等方法
 * -实现了Comparable接口
 * -实现了format和parse方法，以及基于format的toString
 * -提供了判断闰年的工具函数isLeapYear(int year)
 * 
 * 底层实现用到了Date和SimpleDateFormat类
 * 但是Date类有很多问题，易错，考虑在后续版本中改用Calendar实现
 * 
 * 
 */
package StarSky.myutil;
import java.util.*;
import static java.util.Calendar.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class MyDate implements Comparable{
	int year;
	int month;
	int day;//day of month
	//int dayOfWeek;
	
	SimpleDateFormat formatter;
	{
		formatter=new SimpleDateFormat("yyyy-MM-dd");
	}
	
	final int[][] DAYS_IN_MONTH= 
		{{-1,31,28,31,30,31,30,31,31,30,31,30,31},//non-leap year
			{-1,31,29,31,30,31,30,31,31,30,31,30,31}};//leap year
	
	//默认构造函数，当前日期
	public MyDate(){
		Calendar cld=Calendar.getInstance();
		year=cld.get(YEAR);
		month=cld.get(MONTH)+1;//注意，calendar中返回的月份是从0开始的
		day=cld.get(DAY_OF_MONTH);
	}
	public MyDate(int year,int month,int day){
		setYear(year);
		setMonth(month);
		setDay(day);
	}
	@SuppressWarnings("deprecation")
	public MyDate(Date date){
		year=date.getYear()+1900;
		month=date.getMonth()+1;
		day=date.getDate();
	}
	
	//判断是否为闰年
	public static boolean isLeapYear(int year) {
		if(year%4==0) {
			if(year%100==0) {
				if(year%400==0) 
					return true;
				else
					return false;
			}else
				return true;
		}
		return false;
	}
	public boolean isLeapYear() {
		return isLeapYear(year);
	}
	//for c-style programming
	protected int _isLeapYear() {
		if(isLeapYear(year))
			return 1;
		else
			return 0;
	}
	public void setYear(int year) {
		this.year=year;
	}
	public void setMonth(int month) {
		if(month<1 || month>12)
			throw new IllegalArgumentException("invalid month!");
		this.month=month;
	}
	public void setDay(int day) {
		//繁琐的分类讨论
		if(day<1 || day>DAYS_IN_MONTH[_isLeapYear()][month])
			throw new IllegalArgumentException("invalid day!");
		this.day=day;
	}
	public void setDate(int year,int month,int day) {
		setYear(year);
		setMonth(month);
		setDay(day);
	}
	
	//小心地提防日期的特殊“进位制”
	public void addDay(int dayAdded) {
		day+=dayAdded;
		while(day>DAYS_IN_MONTH[_isLeapYear()][month]) {
			day-=DAYS_IN_MONTH[_isLeapYear()][month];
			addMonth(1);
		}
	}
	public void addMonth(int monthAdded) {
		month+=monthAdded;
		
		if(month>12) {
			addYear(month/12);
			month=month%12;
		}
	}
	
	public void addYear(int yearAdded) {
		if(month==2 && day==29)
			day=28;
		year+=yearAdded;
	}
	public int getYear() {
		return year;
	}
	public int getMonth(){
		return month;
	}
	public int getDay() {
		return day;
	}
	public int getDayOfWeek() {
		Calendar cld=Calendar.getInstance();
		cld.set(year,month-1,day);//再次注意，calendar中月份的定义从零开始
		int dayOfWeek=cld.get(DAY_OF_WEEK);
		//calendar中星期几是从周日开始算的，周日为1，周一为2，周六为7；这里改变其值以符合国人习惯
		dayOfWeek--;
		if(dayOfWeek==0)
			dayOfWeek=7;
		return dayOfWeek;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MyDate))
			return false;
		MyDate another=(MyDate)obj;
		return (year==another.year &&
				month==another.month &&
				day==another.day);
	}
	@Override
	public int hashCode() {
		return (year*365+month*30+day)%Integer.MAX_VALUE;
	}
	
	@SuppressWarnings("deprecation")
	public String format(String format) {
		//注意，simpledateformat中m表示分钟，M才表示月份；
		//但mydate中只有日期，没有时间表示，因此为避免错误，将所有m转换为M
		SimpleDateFormat formatter=new SimpleDateFormat(format.replace('m', 'M'));
		return formatter.format(new Date(year-1900,month-1,day));
		//再再次注意，java库中日期相关类中month是从0开始的
	}
	public void setFormat(String format) {
		formatter=new SimpleDateFormat(format.replace('m', 'M'));
	}
	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		Calendar cld=Calendar.getInstance();
		cld.set(year,month-1,day);
		return formatter.format(cld.getTime());
	}
	public static MyDate parse(String date_str,String format) {
		try {
			Date date=
					new SimpleDateFormat(format.replace('m', 'M'))
					.parse(date_str);
			return new MyDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("fail to parse!");
			e.printStackTrace();
		}
		return null;
		
	}
	@SuppressWarnings("deprecation")
	public void parse(String date_str) {
		try {
			Date date=formatter.parse(date_str);
			year=date.getYear();
			month=date.getMonth()+1;
			day=date.getDate();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("fail to parse!");
			e.printStackTrace();
		}
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(!(o instanceof MyDate))
			return 1;
		
		MyDate another=(MyDate)o;
		
		if(year!=another.year)
			return year-another.year;
		if(month!=another.month)
			return month-another.month;
		return day-another.day;
	}
}
