package ticket.vo;

public class SystemFinanceVo {
	long totalMember;
	long totalHall;
	long totalOrder;
	// lvlMember[0]为lv0会员人数，lvlMember[1]为lv1会员人数，以此类推
	long[] lvlMember;
	// 总流水额
	double totalConsumption;
	// 结算后收入
	double totalIncome;

	/**
	 * @param totalMember
	 * @param totalHall
	 * @param totalOrder
	 * @param lvlMember
	 * @param totalConsumption
	 * @param totalIncome
	 */
	public SystemFinanceVo(long totalMember, long totalHall, long totalOrder, long[] lvlMember, double totalConsumption,
			double totalIncome) {
		super();
		this.totalMember = totalMember;
		this.totalHall = totalHall;
		this.totalOrder = totalOrder;
		this.lvlMember = lvlMember;
		this.totalConsumption = totalConsumption;
		this.totalIncome = totalIncome;
	}

	/**
	 * @return the totalMember
	 */
	public long getTotalMember() {
		return totalMember;
	}

	/**
	 * @param totalMember
	 *            the totalMember to set
	 */
	public void setTotalMember(long totalMember) {
		this.totalMember = totalMember;
	}

	/**
	 * @return the totalHall
	 */
	public long getTotalHall() {
		return totalHall;
	}

	/**
	 * @param totalHall
	 *            the totalHall to set
	 */
	public void setTotalHall(long totalHall) {
		this.totalHall = totalHall;
	}

	/**
	 * @return the totalOrder
	 */
	public long getTotalOrder() {
		return totalOrder;
	}

	/**
	 * @param totalOrder
	 *            the totalOrder to set
	 */
	public void setTotalOrder(long totalOrder) {
		this.totalOrder = totalOrder;
	}

	/**
	 * @return the lvlMember
	 */
	public long[] getLvlMember() {
		return lvlMember;
	}

	/**
	 * @param lvlMember
	 *            the lvlMember to set
	 */
	public void setLvlMember(long[] lvlMember) {
		this.lvlMember = lvlMember;
	}

	/**
	 * @return the totalConsumption
	 */
	public double getTotalConsumption() {
		return totalConsumption;
	}

	/**
	 * @param totalConsumption
	 *            the totalConsumption to set
	 */
	public void setTotalConsumption(double totalConsumption) {
		this.totalConsumption = totalConsumption;
	}

	/**
	 * @return the totalIncome
	 */
	public double getTotalIncome() {
		return totalIncome;
	}

	/**
	 * @param totalIncome
	 *            the totalIncome to set
	 */
	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

}
