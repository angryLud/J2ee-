package ticket.vo;

public class MemberRegistryVo {
	// 邮箱地址（注册时），用作用户名
	String email;
	// 密码
	String password;
	// 电话
	String telephone;
	// 会员姓名
	String name;
	// 会员性别(0为男，1为女）
	int sex;

	/**
	 * @param email
	 * @param password
	 * @param telephone
	 * @param name
	 * @param sex
	 */
	public MemberRegistryVo(String email, String password, String telephone, String name, int sex) {
		super();
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.name = name;
		this.sex = sex;
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
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

}
