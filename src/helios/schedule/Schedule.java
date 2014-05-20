package helios.schedule;

import helios.date.parsing.DateParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;


/**
 * @author Jason Diamond
 *
 */
public class Schedule implements Comparable<Schedule>
{
	//a schedule has a start and end time, and is composed of shifts
	//a schedule has a name, typically an agent that it belongs to
	
	private Shift interval;
	
	private ArrayList<Shift> shifts;

	private DateParser dateParser;

	/**
	 * Build a Schedule, with a start and end date.
	 * 
	 * @param intervalStart		The schedule's start date.
	 * @param intervalEnd		The schedule's end date.
	 */
	public Schedule(String intervalStart, String intervalEnd)
	{
		shifts = new ArrayList<Shift>();
		
		dateParser = new DateParser();
		
		interval = new Shift(DateParser.convertSQLDateToGregorian(intervalStart), DateParser.convertSQLDateToGregorian(intervalEnd));
	}
	
	/**
	 * Build a Schedule, with a start and end date.
	 * 
	 * @param intervalStart		The schedule's start date.
	 * @param intervalEnd		The schedule's end date.
	 */
	public Schedule(GregorianCalendar intervalStart, GregorianCalendar intervalEnd)
	{
		
		interval = new Shift(intervalStart, intervalEnd);
		shifts = new ArrayList<Shift>();
		
		dateParser = new DateParser();
	}
	
	/**
	 * Add a shift to the schedule.
	 * 
	 * @param newShift		The shift to add.
	 */
	public void addShift(Shift newShift)
	{
		shifts.add(newShift);
	}
	
	/**
	 * Add a shift to this schedule.
	 * 
	 * @param startShiftDate	The shift's start date.
	 * @param endShiftDate		The shift's end date.
	 */
	public void addShift(GregorianCalendar startShiftDate, GregorianCalendar endShiftDate)
	{
		addShift(new Shift(startShiftDate, endShiftDate));
	}
	
	/**
	 * Add a shift to this schedule.
	 * 
	 * @param startShiftDate	The shift's start date.
	 * @param endShiftDate		The shift's end date.
	 */
	public void addShift(String startShiftDate, String endShiftDate)
	{
		addShift(DateParser.convertSQLDateToGregorian(startShiftDate), DateParser.convertSQLDateToGregorian(endShiftDate));
	}
	
	/**
	 * Retrieve a list of shifts within this schedule, sorted by shift start date.
	 * 
	 * @return	The sorted list of shifts.
	 */
	public ArrayList<Shift> getSortedShifts()
	{
		ArrayList<Shift> unsorted = shifts;
		
		Collections.sort(unsorted, new Comparator<Shift>() 
		{
			@Override
			public int compare(Shift arg0, Shift arg1) 
			{
				int retval = 0;
				
				if(arg0.getStartDate().after(arg1.getStartDate()))
				{
					retval = 1;
				}
				
				return retval;
			}
		});
		
		return unsorted;
		
	}
	
	/**
	 * 
	 * Accessor for itnerval's end date.
	 * 
	 * @return	The interval's end date.
	 */
	public ArrayList<Shift> getShifts()
	{
		return shifts;
	}
	

	
	/** 
	 * Stringify the schedule. Contains the schedule start and end dates, as well as any shifts and their intervals.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Schedules: " + DateParser.toSQLDateFormat(interval.getStartDate()) + " => " + DateParser.toSQLDateFormat(interval.getEndDate()));
		sb.append("\n");
		
		for(Shift s : getSortedShifts() )
		{
			sb.append(s);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * Accessor for interval's start date.
	 * 
	 * @return	The interval's start date.
	 */
	public String getStartDate()
	{
		return DateParser.toSQLDateFormat(interval.getStartDate());
	}
	
	/**
	 * 
	 * Accessor for interval's end date.
	 * 
	 * @return	The interval's end date.
	 */
	public String getEndDate()
	{
		return DateParser.toSQLDateFormat(interval.getEndDate());
	}
	
	/**
	 * Accessor for the schedule's interval.
	 * 
	 * @return	A shift describing the start and end dates of the schedule.
	 */
	public Shift getInterval()
	{
		return interval;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateParser == null) ? 0 : dateParser.hashCode());
		result = prime * result
				+ ((interval == null) ? 0 : interval.hashCode());
		result = prime * result + ((shifts == null) ? 0 : shifts.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Schedule)) {
			return false;
		}
		Schedule other = (Schedule) obj;
		if (dateParser == null) {
			if (other.dateParser != null) {
				return false;
			}
		} else if (!dateParser.equals(other.dateParser)) {
			return false;
		}
		if (interval == null) {
			if (other.interval != null) {
				return false;
			}
		} else if (!interval.equals(other.interval)) {
			return false;
		}
		if (shifts == null) {
			if (other.shifts != null) {
				return false;
			}
		} else if (!shifts.equals(other.shifts)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Schedule o) 
	{
		//sort schedules by startdate
		return getStartDate().compareTo(o.getStartDate());
	}	
}
