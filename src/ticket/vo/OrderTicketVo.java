package ticket.vo;

import java.sql.Date;

public class OrderTicketVo {
	// 会员账号
	String email;
	// 购票选项（0为选座购买，1为立即购买）
	int option;
	// 购票数量
	int ticketNum;
	// 订单金额
	double totalPrice;
	// 下单时间
	String orderDate;
	// 演出时间
	String showDate;
	// 场馆编号
	int hallNo;
	// 订单场馆
	String hallName;
	// 演出内容
	String showType;

	/**
	 * @param email
	 * @param option
	 * @param ticketNum
	 * @param totalPrice
	 * @param orderDate
	 * @param showDate
	 * @param hallNo
	 * @param hallName
	 * @param showType
	 */
	public OrderTicketVo(String email, int option, int ticketNum, double totalPrice, String orderDate, String showDate,
			int hallNo, String hallName, String showType) {
		super();
		this.email = email;
		this.option = option;
		this.ticketNum = ticketNum;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.showDate = showDate;
		this.hallNo = hallNo;
		this.hallName = hallName;
		this.showType = showType;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the option
	 */
	public int getOption() {
		return option;
	}

	/**
	 * @param option
	 *            the option to set
	 */
	public void setOption(int option) {
		this.option = option;
	}

	/**
	 * @return the ticketNum
	 */
	public int getTicketNum() {
		return ticketNum;
	}

	/**
	 * @param ticketNum
	 *            the ticketNum to set
	 */
	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	/**
	 * @return the totalPrice
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the showDate
	 */
	public String getShowDate() {
		return showDate;
	}

	/**
	 * @param showDate
	 *            the showDate to set
	 */
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	/**
	 * @return the hallNo
	 */
	public int getHallNo() {
		return hallNo;
	}

	/**
	 * @param hallNo
	 *            the hallNo to set
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
	 * @param hallName
	 *            the hallName to set
	 */
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	/**
	 * @return the showType
	 */
	public String getShowType() {
		return showType;
	}

	/**
	 * @param showType
	 *            the showType to set
	 */
	public void setShowType(String showType) {
		this.showType = showType;
	}

}
