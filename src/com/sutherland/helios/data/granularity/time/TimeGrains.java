package com.sutherland.helios.data.granularity.time;

import java.util.LinkedHashMap;

public abstract class TimeGrains 
{
	public static final LinkedHashMap<String, String> avaliableTimeGrains = new LinkedHashMap<String, String>()
	{
		private static final long serialVersionUID = 4157353852074203745L;
		{
			put("Yearly", "" + YEARLY_GRANULARITY);
			put("Fiscal Yearly", "" + FISCAL_YEARLY_GRANULARITY );
			put("Quarterly", "" + QUARTERLY_GRANULARITY);
			put("Fiscal Quarterly", "" + FISCAL_QUARTERLY_GRANULARITY);
			put("Monthly", "" + MONTHLY_GRANULARITY);
			put("Weekly", "" + WEEKLY_GRANULARITY);
			put("Daily", "" + DAILY_GRANULARITY);
			put("Hourly", "" + HOURLY_GRANULARITY);
		}
	};

	public final static int FISCAL_YEARLY_GRANULARITY = 7;
	public final static int YEARLY_GRANULARITY = 0;
	public final static int MONTHLY_GRANULARITY = 1;
	public final static int QUARTERLY_GRANULARITY = 5;
	public final static int FISCAL_QUARTERLY_GRANULARITY = 6;
	public final static int WEEKLY_GRANULARITY = 2;
	public final static int DAILY_GRANULARITY = 3;
	public final static int HOURLY_GRANULARITY = 4;
}
