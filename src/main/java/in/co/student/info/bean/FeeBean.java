package in.co.student.info.bean;

import java.util.Date;

public class FeeBean extends BaseBean {
	
	private String name;
	private long studentId;
	private String month;
	private long amount;
	private Date date;
	private long totalfee;
	private long paidFee;
	private long balanceFee;
	
	
	
	
	
	
	
	
	

	public long getPaidFee() {
		return paidFee;
	}

	public void setPaidFee(long paidFee) {
		this.paidFee = paidFee;
	}

	public long getBalanceFee() {
		return balanceFee;
	}

	public void setBalanceFee(long balanceFee) {
		this.balanceFee = balanceFee;
	}

	public long getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(long totalfee) {
		this.totalfee = totalfee;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
