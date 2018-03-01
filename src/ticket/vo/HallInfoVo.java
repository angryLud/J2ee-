package ticket.vo;

public class HallInfoVo {
	int hallNo;
	String hallName;
	long totalOrder;
	long onlineOrder;
	long successOrder;
	long cancelledOrder;
	double income;
	
	/**
	 * @param hallNo
	 * @param hallName
	 * @param totalOrder --- 线上线下订单数量之和
	 * @param onlineOrder --- 线上订单数量
	 * @param successOrder
	 * @param cancelledOrder
	 * @param income
	 */
	public HallInfoVo(int hallNo, String hallName, long totalOrder, long onlineOrder, long successOrder,
			long cancelledOrder, double income) {
		super();
		this.hallNo = hallNo;
		this.hallName = hallName;
		this.totalOrder = totalOrder;
		this.onlineOrder = onlineOrder;
		this.successOrder = successOrder;
		this.cancelledOrder = cancelledOrder;
		this.income = income;
	}
	/**
	 * @return the hallNo
	 */
	public int getHallNo() {
		return hallNo;
	}
	/**
	 * @param hallNo the hallNo to set
	 */
	public void setHallNo(int hallNo) {
		this.hallNo = hallNo;
	}
	/**
	 * @return the hallName
	 */
	public String getHallName() {
		return hallName;
	}
	/**
	 * @param hallName the hallName to set
	 */
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	/**
	 * @return the totalOrder
	 */
	public long getTotalOrder() {
		return totalOrder;
	}
	/**
	 * @param totalOrder the totalOrder to set
	 */
	public void setTotalOrder(long totalOrder) {
		this.totalOrder = totalOrder;
	}
	/**
	 * @return the onlineOrder
	 */
	public long getOnlineOrder() {
		return onlineOrder;
	}
	/**
	 * @param onlineOrder the onlineOrder to set
	 */
	public void setOnlineOrder(long onlineOrder) {
		this.onlineOrder = onlineOrder;
	}
	/**
	 * @return the successOrder
	 */
	public long getSuccessOrder() {
		return successOrder;
	}
	/**
	 * @param successOrder the successOrder to set
	 */
	public void setSuccessOrder(long successOrder) {
		this.successOrder = successOrder;
	}
	/**
	 * @return the cancelledOrder
	 */
	public long getCancelledOrder() {
		return cancelledOrder;
	}
	/**
	 * @param cancelledOrder the cancelledOrder to set
	 */
	public void setCancelledOrder(long cancelledOrder) {
		this.cancelledOrder = cancelledOrder;
	}
	/**
	 * @return the income
	 */
	public double getIncome() {
		return income;
	}
	/**
	 * @param income the income to set
	 */
	public void setIncome(double income) {
		this.income = income;
	}

}
