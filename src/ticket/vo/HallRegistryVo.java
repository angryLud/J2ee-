package ticket.vo;

public class HallRegistryVo {
	// 场馆密码
	String password;
	// 场馆名称
	String hallName;
	// 场馆地点
	String address;
	// 普通座数量
	int juniorNum;
	// 高等座数量
	int seniorNum;
	// 结算比例
	double percent;

	/**
	 * @param password
	 * @param hallName
	 * @param address
	 * @param juniorNum
	 * @param seniorNum
	 * @param income
	 * @param percent
	 */
	public HallRegistryVo(String password, String hallName, String address, int juniorNum, int seniorNum,
			double percent) {
		super();
		this.password = password;
		this.hallName = hallName;
		this.address = address;
		this.juniorNum = juniorNum;
		this.seniorNum = seniorNum;

		this.percent = percent;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the juniorNum
	 */
	public int getJuniorNum() {
		return juniorNum;
	}

	/**
	 * @param juniorNum
	 *            the juniorNum to set
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
	 * @param seniorNum
	 *            the seniorNum to set
	 */
	public void setSeniorNum(int seniorNum) {
		this.seniorNum = seniorNum;
	}

	/**
	 * @return the percent
	 */
	public double getPercent() {
		return percent;
	}

	/**
	 * @param percent
	 *            the percent to set
	 */
	public void setPercent(double percent) {
		this.percent = percent;
	}

}
