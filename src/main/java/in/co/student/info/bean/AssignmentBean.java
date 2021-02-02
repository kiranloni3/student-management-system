package in.co.student.info.bean;

import java.util.Date;

public class AssignmentBean extends BaseBean {


	private long subjectId;
	private String subjectName;
	private String assignmentTitle;
	private Date dueDate;
	private String assignmentFile;
	private String description;
	
	
	
	
	
	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getAssignmentTitle() {
		return assignmentTitle;
	}

	public void setAssignmentTitle(String assignmentTitle) {
		this.assignmentTitle = assignmentTitle;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getAssignmentFile() {
		return assignmentFile;
	}

	public void setAssignmentFile(String assignmentFile) {
		this.assignmentFile = assignmentFile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
