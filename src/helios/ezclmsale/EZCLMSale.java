package helios.ezclmsale;

public class EZCLMSale 
{
	private String userID;
	private String orderDate;
	private String orderAmount;
	private String promoCode;
	private String serviceTypeID;
	private String orderType;
	
	public EZCLMSale(String userID, String orderDate, String orderAmount, String promoCode, String serviceTypeID, String orderType) 
	{
		this.userID = userID;
		this.orderDate = orderDate;
		this.orderAmount = orderAmount;
		this.promoCode = promoCode;
		this.serviceTypeID = serviceTypeID;
		this.orderType = orderType;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @return the orderAmount
	 */
	public String getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @return the promoCode
	 */
	public String getPromoCode() {
		return promoCode;
	}

	/**
	 * @return the serviceTypeID
	 */
	public String getServiceTypeID() {
		return serviceTypeID;
	}

	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}

	
}
