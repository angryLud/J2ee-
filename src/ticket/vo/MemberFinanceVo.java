package ticket.vo;

public class MemberFinanceVo {
	String email;
	long totalOrder;
	long paidOrder;
	long cancelledOrder;
	double consumption;
	/**
	 * @param email
	 * @param totalOrder --- 线上订单数量
	 * @param paidOrder
	 * @param cancelledOrder
	 * @param consumption
	 */
	public MemberFinanceVo(String email, long totalOrder, long paidOrder, long cancelledOrder, double consumption) {
		super();
		this.email = email;
		this.totalOrder = totalOrder;
		this.paidOrder = paidOrder;
		this.cancelledOrder = cancelledOrder;
		this.consumption = consumption;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the paidOrder
	 */
	public long getPaidOrder() {
		return paidOrder;
	}
	/**
	 * @param paidOrder the paidOrder to set
	 */
	public void setPaidOrder(long paidOrder) {
		this.paidOrder = paidOrder;
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
	 * @return the consumption
	 */
	public double getConsumption() {
		return consumption;
	}
	/**
	 * @param consumption the consumption to set
	 */
	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}
	

}
