package za.co.payguru.util;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class DateUtil {
	private static final String DEFAULT_DATE_STR = "1970-01-01";
	private static final String DEFAULT_TIME_STR = "08:00:00";
	public static final Date DEFAULT_DATE = Date.valueOf("1970-01-01");
	public static final Time DEFAULT_TIME = Time.valueOf("08:00:00");
	public static final HashMap<Integer, String> WEEKDAYS = new HashMap<Integer, String>(); 
	public static final Date MAX_DATE = Date.valueOf("9999-01-01");
	public static final int JANUARY    = 1;
	public static final int FEBRUARY   = 2;
	public static final int MARCH      = 3;
	public static final int APRIL      = 4;
	public static final int MAY        = 5;
	public static final int JUNE       = 6;
	public static final int JULY       = 7;
	public static final int AUGUST     = 8;
	public static final int SEPTEMBER  = 9;
	public static final int OCTOBER    = 10;
	public static final int NOVEMBER   = 11;
	public static final int DECEMBER   = 12;
	static {
		WEEKDAYS.put(1, "MONDAY");
		WEEKDAYS.put(2, "TUESDAY");
		WEEKDAYS.put(3, "WEDNESDAY");
		WEEKDAYS.put(4, "THURSDAY");
		WEEKDAYS.put(5, "FRIDAY");
		WEEKDAYS.put(6, "SATURDAY");
		WEEKDAYS.put(7, "SUNDAY");
	}

	public static Time getCurrentHHMMSSTime() {
		Time today = Time.valueOf(DEFAULT_TIME_STR);
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			today = Time.valueOf(dtf.format(now)+"");
		}catch (Exception e) {
			System.out.println("Error getting current time: " + e.toString());
		}
		return today;
	}
	public static String getCurrentHHMMSSStr(boolean seperator) {
		String today = new String(DEFAULT_TIME_STR);
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			today = dtf.format(now)+"";
			if(!seperator)
				today = today.replace(":", "");
		}catch (Exception e) {
			System.out.println("Error getting current time: " + e.toString());
		}
		return today;
	}

	public static String getCurrentHHMMSSStr() {
		boolean seperator = true;
		return getCurrentHHMMSSStr(seperator);
	}

	public static Date getCurrentCCYYMMDDDate() {
		Date today = Date.valueOf(DEFAULT_DATE_STR);
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			LocalDateTime now = LocalDateTime.now();  
			today = Date.valueOf(dtf.format(now)+"");
		}catch (Exception e) {
			System.out.println("Error getting current date: " + e.toString());
		}
		return today;
	}

	public static String getCurrentCCYYMMDDStr(boolean seperator, char seperatorChar) {
		String today = new String(DEFAULT_DATE_STR);
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			LocalDateTime now = LocalDateTime.now();  
			today = dtf.format(now)+"";
			if(!seperator)
				today = today.replace("-", "");
			else
				today = today.replace('-', seperatorChar);
		}catch (Exception e) {
			System.out.println("Error getting current date: " + e.toString());
		}
		return today;
	}
	public static String getCurrentCCYYMMDDStr(boolean seperator) {
		String today = new String(DEFAULT_DATE_STR);
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			LocalDateTime now = LocalDateTime.now();  
			today = dtf.format(now)+"";
			if(!seperator)
				today = today.replace("-", "");
			else
				today = today.replace('-', '/');
		}catch (Exception e) {
			System.out.println("Error getting current date: " + e.toString());
		}
		return today;
	}

	public static String getCurrentCCYYMMDDStr() {
		boolean seperator = true;
		char seperatorChar = '/';
		return getCurrentCCYYMMDDStr(seperator, seperatorChar);
	}

	public static Date getPrevDate(Date startDate, int daysBehind, boolean inclStartDate) {
		try {
			LocalDate currDate = startDate.toLocalDate();
			int day = 0;
			if(inclStartDate)
				day = 1;
			for(;day<daysBehind;day++) {
				currDate = currDate.minusDays(1);
			}
			return Date.valueOf(currDate);
		}catch (Exception e) {
			System.out.println("Error Getting PrevDate: " + e.toString());
			return startDate;
		}
	}
	public static Date getPrevDate(Date startDate) {
		return getPrevDate(startDate, 1, false);
	}
	public static Date getPrevDate(Date startDate, int daysBehind) {
		return getPrevDate(startDate, daysBehind, false);
	}
	public static Date minusMonths(Date date, int monthsBack) {
		try {
			LocalDate ld = date.toLocalDate();
			ld = ld.minusMonths(monthsBack);
			date = getDateValue(ld.getYear()+"-"+Util.prefixChar(""+ld.getMonthValue(),'0',2)+"-"+Util.prefixChar(""+ld.getDayOfMonth(),'0',2));
		}catch (Exception e) {}
		return date;
	}
	public static Date getNextDate(Date startDate, int daysAhead) {
		try {
			LocalDate currDate = startDate.toLocalDate();
			for(int i=0;i<daysAhead;i++) {
				currDate = currDate.plusDays(1);
			}
			return Date.valueOf(currDate);
		}catch (Exception e) {
			System.out.println("Error Getting PrevDate: " + e.toString());
			return startDate;
		}
	}

	public static Date getNextDate(Date startDate) {
		return getNextDate(startDate, 1);
	}

	public static Date getDateValue(String dateStr) {
		Date date = DEFAULT_DATE;
		for(int z=0;z<1;z++) {
			if(dateStr==null)
				break;
			try {
				if(dateStr.indexOf("/")>0)
					dateStr = dateStr.replace("/", "-");
				if(Util.countCharOccurancesInString(dateStr, '-') != 2)
					break;
				if(dateStr.indexOf("-")==4 && dateStr.lastIndexOf("-")==7)
					date = Date.valueOf(dateStr);
				else {
					if(dateStr.indexOf("-")==2 && dateStr.lastIndexOf("-")==5) {
						String year = Util.getValueAt(dateStr, "-", 2);
						String month = Util.getValueAt(dateStr, "-", 1);
						String day = Util.getValueAt(dateStr, "-", 0);
						date = Date.valueOf(year+"-"+month+"-"+day);
					}else {
						break;
					}
				}
			}catch (Exception e) {
				System.out.println("Error parsing date: " + e.toString());
			}
		}
		return date;
	}
	public static int getTotDaysInMonth(Date date) {
		int days = 0;
		try {
			LocalDate lc = date.toLocalDate();
			days = lc.lengthOfMonth();
		}catch (Exception e) {
		}
		return days;
	}
	public static int getDayOfMonth(Date date) {
		int day = 0;
		try {
			LocalDate lc = date.toLocalDate();
			day = lc.getDayOfMonth();
		}catch (Exception e) {
		}
		return day;
	}
	public static int getWeekDayInt(Date date) {
		int day = 0;
		try {
			LocalDate lc = date.toLocalDate();
			day = lc.getDayOfWeek().getValue();
		}catch (Exception e) {
		}
		return day;
	}
	public static boolean isLastDayOfMonth(Date date) {
		boolean lastDay = false;
		try {
			LocalDate lc = date.toLocalDate();
			int currDayVal = lc.getDayOfMonth();
			int lastDayVal = lc.lengthOfMonth();
			if(currDayVal==lastDayVal)
				lastDay = true;
		}catch (Exception e) {
		}
		return lastDay;
	}
	public static Time parseTime(String timeVal) {
		Time time = DateUtil.DEFAULT_TIME;
		try {
			if(timeVal.length()==5)
				timeVal = timeVal.concat(":00");
			time = Time.valueOf(timeVal);
		}catch (Exception e) {
		}
		return time;
	}
	//is compareDate before initialDate
	public static boolean dateBefore(String initialDate, String compareDate) {
		boolean before = false;
		if(initialDate==null||compareDate==null||initialDate.length()<=0||compareDate.length()<=0)
			return before;
		try {
			if(initialDate.contains("/"))
				initialDate = initialDate.replace("/", "-");
			if(compareDate.contains("/"))
				compareDate = compareDate.replace("/", "-");
			if(initialDate.compareTo(compareDate)>0)
				before = true;
		}catch (Exception e) {}
		return before;
	}
	public static void main(String[] args) {
		System.out.println(dateBefore("2024/02/07","2024/02/06"));
	}
	public static Date getStartOfMonth(Date date) {
		Date startDate = getCurrentCCYYMMDDDate();
		try {
			LocalDate ld = date.toLocalDate();
			String day = "01";
			int monthVal = ld.getMonthValue();
			int yearVal = ld.getYear();
			startDate = getDateValue(yearVal+"-"+Util.prefixChar(""+monthVal,'0',2)+"-"+day);
		}catch (Exception e) {}
		return startDate;
	}
	public static Date getStartOfMonth() {
		return getStartOfMonth(getCurrentCCYYMMDDDate());
	}
	public static Date getStartOfYear(Date date) {
		Date startDate = getCurrentCCYYMMDDDate();
		try {
			LocalDate ld = date.toLocalDate();
			String day = "01";
			String monthVal = "01";
			int yearVal = ld.getYear();
			startDate = getDateValue(yearVal+"-"+monthVal+"-"+day);
		}catch (Exception e) {}
		return startDate;
	}
	public static Date getStartOfYear() {
		return getStartOfYear(getCurrentCCYYMMDDDate());	
	}

	public static Date getStartPrevMonth(Date date) {
		Date prevDate = getCurrentCCYYMMDDDate();
		try {
			LocalDate ld = date.toLocalDate();
			ld = ld.minusMonths(1);
			int monthVal = ld.getMonthValue();
			int yearVal = ld.getYear();
			String day = "01";
			String month = Util.prefixChar(""+(monthVal),'0',2);
			String year = ""+yearVal;
			prevDate = getDateValue(year+"-"+month+"-"+day);
		}catch (Exception e) {}
		return prevDate;
	}
	public static Date getEndMonth(Date date) {
		Date endMonthDate = getCurrentCCYYMMDDDate();
		try {
			LocalDate ld = date.toLocalDate();
			endMonthDate = DateUtil.getDateValue(ld.getYear()+"-"+Util.prefixChar(""+ld.getMonthValue(), '0', 2)+"-"+ld.lengthOfMonth());
		}catch (Exception e) {}
		return endMonthDate;
	}
	public static Date getEndMonth() {
		return getEndMonth(getCurrentCCYYMMDDDate());
	}
	public static Date getEndYear(Date date) {
		Date endYearDate = getCurrentCCYYMMDDDate();
		try {
			LocalDate ld = date.toLocalDate();
			endYearDate = DateUtil.getDateValue(ld.getYear()+"-12-31");
		}catch (Exception e) {}
		return endYearDate;
	}
	public static Date getEndPrevMonth(Date date) {
		Date prevDate = getCurrentCCYYMMDDDate();
		try {
			LocalDate ld = date.toLocalDate();
			ld = ld.minusMonths(1);
			int monthVal = ld.getMonthValue();
			int yearVal = ld.getYear();
			int dayVal = ld.lengthOfMonth();
			String month = Util.prefixChar(""+(monthVal),'0',2);
			String year = ""+yearVal;
			String day = Util.prefixChar(""+dayVal, '0', 2);
			prevDate = getDateValue(year+"-"+month+"-"+day);
		}catch (Exception e) {}
		return prevDate;
	}
	public static Date getStartPrevMonth() {
		return getStartPrevMonth(getCurrentCCYYMMDDDate());
	}
	public static Date getLastDateOfMonth(Date date) {
		Date lastDateOfMonth = getCurrentCCYYMMDDDate();
		try {
			LocalDate ld = date.toLocalDate();
			int lastDay = getTotDaysInMonth(date);
			String dateVal = ld.getYear()+"-"+Util.prefixChar(""+ld.getMonthValue(),'0',2)+"-"+Util.prefixChar(""+lastDay,'0',2);
			lastDateOfMonth = getDateValue(dateVal);
		}catch (Exception e) {System.out.println(e.toString());}
		return lastDateOfMonth;
	}
	public static Date getPrevMonthCurrDate() {
		return getPrevMonthCurrDate(DateUtil.getCurrentCCYYMMDDDate());
	}
	public static Date getPrevMonthCurrDate(Date date) {
		Date lastMonthDate = getCurrentCCYYMMDDDate();
		try {
			LocalDate ld = date.toLocalDate();
			ld = ld.minusMonths(1);
			int	dayVal = ld.getDayOfMonth();
			int monthVal = ld.getMonthValue();
			int yearVal = ld.getYear();
			lastMonthDate = getDateValue(yearVal+"-"+Util.prefixChar(""+monthVal, '0', 2)+"-"+Util.prefixChar(""+dayVal, '0', 2));
		}catch (Exception e) {}
		return lastMonthDate;
	}

	public static String getMonth(int monthVal) {
		String month = "JAN";
		if(monthVal==FEBRUARY)
			month = "FEB";
		else if(monthVal==MARCH)
			month = "MAR";
		else if(monthVal==APRIL)
			month = "APR";
		else if(monthVal==MAY)
			month = "MAY";
		else if(monthVal==JUNE)
			month = "JUN";
		else if(monthVal==JULY)
			month = "JUL";
		else if(monthVal==AUGUST)
			month = "AUG";
		else if(monthVal==SEPTEMBER)
			month = "SEP";
		else if(monthVal==OCTOBER)
			month = "OCT";
		else if(monthVal==NOVEMBER)
			month = "NOV";
		else if(monthVal==DECEMBER)
			month = "DEC";
		return month;
	}
	public static int getYear() {
		Date today = getCurrentCCYYMMDDDate();
		LocalDate ld = today.toLocalDate();
		return ld.getYear();
	}
	public static int getPrevYear() {
		Date today = getCurrentCCYYMMDDDate();
		LocalDate ld = today.toLocalDate();
		ld = ld.minusYears(1);
		return ld.getYear();
	}
	public static int getPrevMonth() {
		Date today = getCurrentCCYYMMDDDate();
		LocalDate ld = today.toLocalDate();
		ld = ld.minusMonths(1);
		return ld.getMonthValue();
	}
	public static int getMonth() {
		Date today = getCurrentCCYYMMDDDate();
		LocalDate ld = today.toLocalDate();
		return ld.getMonthValue();
	}
}
