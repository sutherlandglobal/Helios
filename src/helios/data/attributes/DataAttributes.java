package helios.data.attributes;

public interface DataAttributes 
{
	//sales
	public final static String SALES_AMTS_ATTR = "salesAmounts";
	public final static String SALES_COUNT_ATTR = "salesCount";
	public final static String NET_REV_ATTR = "netRevenue";
	public final static String NET_SALES_COUNT_ATTR = "netSalesCount";
	public final static String CONVERSION_ATTR = "conversion";
	public final static String REV_PER_CALL_ATTR = "revenuePerCall";
	public final static String AVG_ORDER_VAL_ATTR = "averageOrderValue";
	
	//call volume
	public final static String CALL_VOL_ATTR = "callVolume";
		
	//surveys
	public final static String IVR_CSAT_ATTR = "ivrCSAT";
	public final static String DSAT_CASE_ATTR = "dsatCases";
	public final static String LMI_DSAT_CASE_ATTR = "lmiDsatCases";
	public final static String IVR_DSAT_CASE_ATTR = "ivrDsatCases";
	public final static String CSAT_SURVEY_VOL_ATTR = "csatSurveyVolume";
	public final static String SURVEY_VOL_ATTR = "surveyVolume";
	public final static String SAT_SURVEYS_ATTR = "satSurveys";
	public final static String ALL_SURVEYS_ATTR = "allSurveys";


	//schedules
	public final static String SCHEDULE_ADH_ATTR = "scheduleAdherence";
	public final static String LATE_DAYS_ATTR = "lateDays";
	public final static String MINS_LATE_ATTR = "minutesLate";
	public final static String MINS_WORKED_ATTR = "minutesWorked";
	
	//issues
	public final static String SOLD_ISSUES_ATTR = "soldIssues";
	public final static String USED_ISSUES_ATTR = "usedIssues";
	
	//refunds
	public final static String REFUNDS_AMTS_ATTR = "refundAmounts";
	public final static String REFUND_COUNT_ATTR = "refundCount";
	public final static String TOTAL_REFUNDS_ATTR = "totalRefunds";
	
	//handle/talk time
	public final static String ACW_TIME_ATTR = "acwTime";
	public final static String ACD_TIME_ATTR = "acdTime";
	public final static String HANDLE_TIME_ATTR = "handleTime";
	public final static String TALK_TIME_ATTR = "talkTime";
	
	//cases
	public final static String CASE_IDS_ATTR = "caseIDs";
	public final static String CREATED_CASES_ATTR = "createdCases";
	public final static String UPDATED_CASES_ATTR = "updatedCases";

	//PINs
	public final static String PINS_CONSUMED_ATTR = "pinsConsumed";
	
	//Customers
	public final static String CREATED_CUST_ATTR = "createdCustomers";
	
	//datum identifiers
	public final static String NAME_ATTR = "userName";
	public final static String DATE_ATTR = "date";

	//documentations
	public final static String SALES_DOC_COUNT_ATTR = "salesDocCount";
	public final static String DOC_COUNT_ATTR = "docCount";
}
