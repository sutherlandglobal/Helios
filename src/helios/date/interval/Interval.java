package helios.date.interval;

public class Interval 
{
	private String intervalName;
	private String startDate;
	private String endDate;
	
	public Interval(String intervalName, String startDate, String endDate) {
		super();
		this.intervalName = intervalName;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public String getIntervalName() {
		return intervalName;
	}



	public void setIntervalName(String intervalName) {
		this.intervalName = intervalName;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
