package ticket.vo;

import java.util.List;

public class SeatConditionVo {
	char[][] map;
	List<String> unavailable;
	/**
	 * @param map
	 * @param unavailable
	 */
	public SeatConditionVo(char[][] map, List<String> unavailable) {
		super();
		this.map = map;
		this.unavailable = unavailable;
	}
	/**
	 * @return the map
	 */
	public char[][] getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(char[][] map) {
		this.map = map;
	}
	/**
	 * @return the unavailable
	 */
	public List<String> getUnavailable() {
		return unavailable;
	}
	/**
	 * @param unavailable the unavailable to set
	 */
	public void setUnavailable(List<String> unavailable) {
		this.unavailable = unavailable;
	}
	
	
	
	
	
	

}
