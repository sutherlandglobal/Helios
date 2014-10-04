package com.sutherland.helios.schedule;

import java.util.GregorianCalendar;

import com.sutherland.helios.date.parsing.DateParser;


/**
 * @author Jason Diamond
 *
 */
public class Shift 
{
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private DateParser dateParser;
	
	/**
	 * Build an empty shift.
	 */
	protected Shift(){};
	
	/**
	 * 
	 * Build a shift with a supplied interval.
	 * 
	 * @param intervalStart	Date of interval's beginning.
	 * @param intervalEnd	Date of interval's end.
	 */
	public Shift(GregorianCalendar intervalStart, GregorianCalendar intervalEnd)
	{
		dateParser = new DateParser();
		
		if(intervalStart.before(intervalEnd))
		{
				this.startDate = intervalStart;
				this.endDate = intervalEnd;
		}
		else
		{
			this.startDate = intervalEnd;
			this.endDate = intervalStart;
		}
	}
	
	/**
	 * 
	 * Build a shift with a supplied interval.
	 * 
	 * @param intervalStart	Date of interval's beginning.
	 * @param intervalEnd	Date of interval's end.
	 */
	public Shift(String intervalStart, String intervalEnd)
	{
		dateParser = new DateParser();
		
		GregorianCalendar intervalStartDate =  DateParser.convertSQLDateToGregorian((intervalStart));
		GregorianCalendar intervalEndDate =  DateParser.convertSQLDateToGregorian((intervalEnd));
		
		if(intervalStartDate.before(intervalEndDate))
		{
				this.startDate = intervalStartDate;
				this.endDate = intervalEndDate;
		}
		else
		{
			this.startDate = intervalEndDate;
			this.endDate = intervalStartDate;
		}
	}
	
	/**
	 * 
	 * Accessor for the interval's start date.
	 * 
	 * @return	The interval's start date.
	 */
	public GregorianCalendar getStartDate() 
	{
		return startDate;
	}

	/**
	 * 
	 * Accessor for itnerval's end date.
	 * 
	 * @return	The interval's end date.
	 */
	public GregorianCalendar getEndDate() 
	{
		return endDate;
	}
	

	/* 
	 * Stringify this shift
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Shift StartDate: " + DateParser.toSQLDateFormat(startDate));
		sb.append(" => EndDate: " + DateParser.toSQLDateFormat(endDate));
		
		return sb.toString();
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
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
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
		if (!(obj instanceof Shift)) {
			return false;
		}
		Shift other = (Shift) obj;
		if (dateParser == null) {
			if (other.dateParser != null) {
				return false;
			}
		} else if (!dateParser.equals(other.dateParser)) {
			return false;
		}
		if (endDate == null) {
			if (other.endDate != null) {
				return false;
			}
		} else if (!endDate.equals(other.endDate)) {
			return false;
		}
		if (startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!startDate.equals(other.startDate)) {
			return false;
		}
		return true;
	}
	


//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) 
//	{
//		GregorianCalendar x = new GregorianCalendar();
//		GregorianCalendar y = new GregorianCalendar();
//		
//		y.add(Calendar.DAY_OF_MONTH, 1);
//		
//		Shift s = new Shift(x, y);
//		System.out.println(s);
//		
//		Shift t = new Shift(y, x);
//		System.out.println(t);
//		
//	}

}
