package ticket.vo;

import ticket.model.Plan;

public class UserOrderVo {
	// 计划编号
	int planNo;
	// 会员账号
	String email;
	// 会员积分
	int point;
	// 场馆编码
	int hallNo;
	// 场馆名称
	String hallName;
	// 场馆地点
	String address;
	// 演出时间
	String showDate;
	// 剩余普通座数量
	int juniorNum;
	// 剩余高等座数量
	int seniorNum;
	// 普通座价格
	double juniorPrice;
	// 高等座价格
	double seniorPrice;
	// 演出类型
	String showType;
	// 内容描述
	String description;
	
	
	/**
	 * @param planNo
	 * @param email
	 * @param hallNo
	 * @param hallName
	 * @param address
	 * @param showDate
	 * @param juniorNum
	 * @param seniorNum
	 * @param juniorPrice
	 * @param seniorPrice
	 * @param showType
	 * @param description
	 */
	public UserOrderVo(int planNo, String email, int point, int hallNo, String hallName, String address, String showDate,
			int juniorNum, int seniorNum, double juniorPrice, double seniorPrice, String showType, String description) {
		super();
		this.planNo = planNo;
		this.email = email;
		this.point = point;
		this.hallNo = hallNo;
		this.hallName = hallName;
		this.address = address;
		this.showDate = showDate;
		this.juniorNum = juniorNum;
		this.seniorNum = seniorNum;
		this.juniorPrice = juniorPrice;
		this.seniorPrice = seniorPrice;
		this.showType = showType;
		this.description = description;
	}
	
	public UserOrderVo(String email, int point, Plan plan) {
		this.planNo = plan.getPlanNo();
		this.email = email;
		this.point = point;
		this.hallNo = plan.getHallNo();
		this.hallName = plan.getHallName();
		this.address = plan.getAddress();
		this.showDate = plan.getShowDate();
		this.juniorNum = plan.getJuniorNum();
		this.seniorNum = plan.getSeniorNum();
		this.juniorPrice = plan.getJuniorPrice();
		this.seniorPrice = plan.getSeniorPrice();
		this.showType = plan.getShowType();
		this.description = plan.getDescription();
		
	}
	/**
	 * @return the planNo
	 */
	public int getPlanNo() {
		return planNo;
	}
	/**
	 * @param planNo the planNo to set
	 */
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the juniorNum
	 */
	public int getJuniorNum() {
		return juniorNum;
	}
	/**
	 * @param juniorNum the juniorNum to set
	 */
	public void setJuniorNum(int juniorNum) {
		this.juniorNum = juniorNum;
	}
	/**
	 * @return the seniorNum
	 */
	public int getSeniorNum() {
		return seniorNum;
	}
	/**
	 * @param seniorNum the seniorNum to set
	 */
	public void setSeniorNum(int seniorNum) {
		this.seniorNum = seniorNum;
	}
	/**
	 * @return the juniorPrice
	 */
	public double getJuniorPrice() {
		return juniorPrice;
	}
	/**
	 * @param juniorPrice the juniorPrice to set
	 */
	public void setJuniorPrice(double juniorPrice) {
		this.juniorPrice = juniorPrice;
	}
	/**
	 * @return the seniorPrice
	 */
	public double getSeniorPrice() {
		return seniorPrice;
	}
	/**
	 * @param seniorPrice the seniorPrice to set
	 */
	public void setSeniorPrice(double seniorPrice) {
		this.seniorPrice = seniorPrice;
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
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	
}
