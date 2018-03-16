package ticket.vo;

public class SettleOrderVo {
	int hallNo;
	String hallName;
	int unsettledNum;
	
	
	/**
	 * @param hallNo
	 * @param hallName
	 * @param unsettledNum
	 */
	public SettleOrderVo(int hallNo, String hallName, int unsettledNum) {
		super();
		this.hallNo = hallNo;
		this.hallName = hallName;
		this.unsettledNum = unsettledNum;
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
	 * @return the unsettledNum
	 */
	public int getUnsettledNum() {
		return unsettledNum;
	}
	/**
	 * @param unsettledNum the unsettledNum to set
	 */
	public void setUnsettledNum(int unsettledNum) {
		this.unsettledNum = unsettledNum;
	}
	
	

}
