package in.co.student.info.controller;

public interface SIMView {
	
	public String APP_CONTEXT = "/StudentManagementSystem";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";
	
	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	public String SUBJECT_VIEW=PAGE_FOLDER+"/SubjectView.jsp";
	public String SUBJECT_LIST_VIEW=PAGE_FOLDER+"/SubjectListView.jsp";
	
	public String FEE_VIEW=PAGE_FOLDER+"/FeeView.jsp";
	public String FEE_LIST_VIEW=PAGE_FOLDER+"/FeeListView.jsp";
	
	public String ATTENDANCE_VIEW=PAGE_FOLDER+"/AttendanceView.jsp";
	public String ATTENDANCE_LIST_VIEW=PAGE_FOLDER+"/AttendanceListView.jsp";
	
	public String TEACHER_LIST_VIEW=PAGE_FOLDER+"/TeacherListView.jsp";
	
	public String ASSIGNMENT_VIEW=PAGE_FOLDER+"/AssignmentView.jsp";
	public String ASSIGNMENT_LIST_VIEW=PAGE_FOLDER+"/AssignmentListView.jsp";
	
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/WelcomeView.jsp";
	public String ABOUT_VIEW = PAGE_FOLDER + "/AboutUs.jsp";
	public String INDEX_VIEW ="/index.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";


	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";
	 
	public String SUBJECT_CTL=APP_CONTEXT+"/ctl/SubjectCtl";
    public String SUBJECT_LIST_CTL=APP_CONTEXT+"/ctl/SubjectListCtl";
    
    public String ASSIGNMENT_CTL=APP_CONTEXT+"/ctl/AssignmentCtl";
    public String ASSIGNMENT_LIST_CTL=APP_CONTEXT+"/ctl/AssignmentListCtl";
    
    public String ATTENDANCE_CTL=APP_CONTEXT+"/ctl/AttendanceCtl";
    public String ATTENDANCE_LIST_CTL=APP_CONTEXT+"/ctl/AttendanceListCtl";
  
    public String FEE_CTL=APP_CONTEXT+"/ctl/FeeCtl";
    public String FEE_LIST_CTL=APP_CONTEXT+"/ctl/FeeListCtl";
    
    public String TEACHER_LIST_CTL=APP_CONTEXT+"/ctl/TeacherListCtl";
  
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String AboutUsCtl = APP_CONTEXT + "/AboutUsCtl";
	public String INDEX_CTL = APP_CONTEXT + "/IndexCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";

	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";


}
