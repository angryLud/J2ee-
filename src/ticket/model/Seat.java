package ticket.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "seat")
public class Seat {
	// 座位号
	int seatNo;
	// 计划编号
	int planNo;
	// 座位行号
	int srow;
	// 座位列号
	int scolumn;
	// 座位种类(0为普通座，1为高等座)
	int type;
	// 是否被订(0为未订，1为已订)
	int occupied;

	public Seat() {

	}
	

	/**
	 * @return the seatNo
	 */
	@Id
	public int getSeatNo() {
		return seatNo;
	}


	/**
	 * @param seatNo the seatNo to set
	 */
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}


	/**
	 * @return the planNo
	 */
	public int getPlanNo() {
		return planNo;
	}

	/**
	 * @param planNo
	 *            the planNo to set
	 */
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}

	/**
	 * @return the srow
	 */
	public int getSrow() {
		return srow;
	}

	/**
	 * @param srow
	 *            the srow to set
	 */
	public void setSrow(int srow) {
		this.srow = srow;
	}

	/**
	 * @return the scolumn
	 */
	public int getScolumn() {
		return scolumn;
	}

	/**
	 * @param scolumn
	 *            the scolumn to set
	 */
	public void setScolumn(int scolumn) {
		this.scolumn = scolumn;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the occupied
	 */
	public int getOccupied() {
		return occupied;
	}

	/**
	 * @param occupied
	 *            the occupied to set
	 */
	public void setOccupied(int occupied) {
		this.occupied = occupied;
	}

}
