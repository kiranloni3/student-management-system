package in.co.student.info.bean;

import java.sql.Timestamp;
import java.util.Date;

/**
 * User JavaBean encapsulates TimeTable attributes
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */
public class UserBean extends BaseBean {

	/**
	 * Lock Active constant for User
	 */
	public static final String ACTIVE = "Active";
	/**
	 * Lock Inactive constant for User
	 */
	public static final String INACTIVE = "Inactive";
	/**
	 * First Name of User
	 */
	private String firstName;
	/**
	 * Last Name of User
	 */
	private String lastName;
	/**
	 * Login of User
	 */
	private String login;
	/**
	 * Password of User
	 */
	private String password;
	/**
	 * Confirm Password of User
	 */
	private String confirmPassword;
	/**
	 * Date of Birth of User
	 */
	private Date dob;
	/**
	 * MobielNo of User
	 */
	private String mobileNo;
	/**
	 * Role of User
	 */
	private long roleId;
	/**
	 * Number of unsuccessful login attempt
	 */
	/**
	 * Gender of User
	 */
	private String gender;
	
	private String emailId;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String image;
	private String school;
	private String course;
	
	
	
	
	
	
	


	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return FirstName Of User
	 */

	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param FirstName
	 *            To set User FirstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return LastName Of User
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param LastName
	 *            To set User LastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return Login id Of User
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param Login
	 *            Id To set User Login ID
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return Password Of User
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param Password
	 *            To set User Password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Confirm Password Of User
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param Confirm
	 *            PAssword To set User Confirm Password
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return Date Of Birth Of User
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param Date
	 *            Of Birth To set User Date Of Birth
	 */

	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return Mobile No Of User
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param Mobile
	 *            No To set User Mobile No
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return ROle Id Of User
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * @param Role
	 *            Id To set User ROle Id
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	
	

	/**
	 * @return unSuccessfulLogin Of User
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param Gender
	 *            To set User Gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	

	public String getKey() {
		return id + "";
	}

	public String getValue() {

		return firstName + " " + lastName;
	}
}
