package ticket.vo;

public class ViewOrderVo {
	// 订单号
	int orderid;
	// 订票渠道（0表示线上预订，1表示线下购票）
	int orderMethod;
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
	// 支付状态（0为未支付，1为已支付,线下购票默认已支付）
	int payState;
	// 配票状态（0为失败，1为成功，选座购买默认成功）
	int allocState;
	// 场馆编号
	int hallNo;
	// 订单场馆
	String hallName;
	// 演出内容
	String showType;
	/**
	 * @param orderid
	 * @param orderMethod
	 * @param option
	 * @param ticketNum
	 * @param totalPrice
	 * @param orderDate
	 * @param showDate
	 * @param payState
	 * @param allocState
	 * @param hallNo
	 * @param hallName
	 * @param showType
	 */
	public ViewOrderVo(int orderid, int orderMethod, int option, int ticketNum, double totalPrice, String orderDate,
			String showDate, int payState, int allocState, int hallNo, String hallName, String showType) {
		super();
		this.orderid = orderid;
		this.orderMethod = orderMethod;
		this.option = option;
		this.ticketNum = ticketNum;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.showDate = showDate;
		this.payState = payState;
		this.allocState = allocState;
		this.hallNo = hallNo;
		this.hallName = hallName;
		this.showType = showType;
	}
	/**
	 * @return the orderid
	 */
	public int getOrderid() {
		return orderid;
	}
	/**
	 * @param orderid the orderid to set
	 */
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	/**
	 * @return the orderMethod
	 */
	public int getOrderMethod() {
		return orderMethod;
	}
	/**
	 * @param orderMethod the orderMethod to set
	 */
	public void setOrderMethod(int orderMethod) {
		this.orderMethod = orderMethod;
	}
	/**
	 * @return the option
	 */
	public int getOption() {
		return option;
	}
	/**
	 * @param option the option to set
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
	 * @param ticketNum the ticketNum to set
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
	 * @param totalPrice the totalPrice to set
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
	 * @param orderDate the orderDate to set
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
	 * @param showDate the showDate to set
	 */
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	/**
	 * @return the payState
	 */
	public int getPayState() {
		return payState;
	}
	/**
	 * @param payState the payState to set
	 */
	public void setPayState(int payState) {
		this.payState = payState;
	}
	/**
	 * @return the allocState
	 */
	public int getAllocState() {
		return allocState;
	}
	/**
	 * @param allocState the allocState to set
	 */
	public void setAllocState(int allocState) {
		this.allocState = allocState;
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
	 * @return the showType
	 */
	public String getShowType() {
		return showType;
	}
	/**
	 * @param showType the showType to set
	 */
	public void setShowType(String showType) {
		this.showType = showType;
	}
	
	

}
