package fundstarterServer;

import java.util.Calendar;
import java.util.Timer;

public class Time_stuff {
	public Time_stuff()
	{
		Timer timer =  new Timer();
		Calendar date = Calendar.getInstance();
		date.set(
		  Calendar.AM_PM,
		  Calendar.AM
		);
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		// Schedule to run every Sunday in midnight
		timer.schedule(new Check_Projects( new Thread()), date.getTime(),1000 * 60 * 60 * 24 * 7);
	}
}
