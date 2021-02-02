<%@page import="in.co.student.info.controller.LoginCtl"%>
<%@page import="in.co.student.info.bean.UserBean"%>
<%@page import="in.co.student.info.controller.SIMView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

 <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="/StudentManagementSystem/css/bootstrap.css">
        <link rel="stylesheet" href="/StudentManagementSystem/vendors/linericon/style.css">
        <link rel="stylesheet" href="/StudentManagementSystem/css/font-awesome.min.css">
        <link rel="stylesheet" href="/StudentManagementSystem/vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="/StudentManagementSystem/vendors/lightbox/simpleLightbox.css">
        <link rel="stylesheet" href="/StudentManagementSystem/vendors/nice-select/css/nice-select.css">
        <link rel="stylesheet" href="/StudentManagementSystem/vendors/animate-css/animate.css">
        <link rel="stylesheet" href="/StudentManagementSystem/vendors/popup/magnific-popup.css">
        <!-- main css -->
        <link rel="stylesheet" href="/StudentManagementSystem/css/style.css">
        <link rel="stylesheet" href="/StudentManagementSystem/css/responsive.css">
        <link rel="stylesheet" href="/StudentManagementSystem/css/login.css">
        <script src="/StudentManagementSystem/js/jquery-3.3.1.min.js"></script>
        <script src="/StudentManagementSystem/js/jquery.min.js"></script>
</head>
<body>
<%
	UserBean userDto = (UserBean) session.getAttribute("user");

	boolean userLoggedIn = userDto != null;

	String welcomeMsg = "Hi, ";

	if (userLoggedIn) { 
		welcomeMsg += userDto.getFirstName();
	} else {
		welcomeMsg += "Guest";
	}
%>
       <header class="header_area">
          	
            <div class="main_menu">
            	<nav class="navbar navbar-expand-lg navbar-light">
					<div class="container">
						<!-- Brand and toggle get grouped for better mobile display -->
						<a class="navbar-brand logo_h" href="<%=SIMView.WELCOME_CTL%>">Student Management System</a>
						
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
							<ul class="nav navbar-nav menu_nav ml-auto">
								<li class="nav-item active"><a class="nav-link" href="<%=SIMView.WELCOME_CTL%>">Home</a></li> 
								<li class="nav-item"><a class="nav-link" href="<%=SIMView.AboutUsCtl%>">About Us</a></li> 
								
						<%
				if (userLoggedIn) {
				%> 
				
				<%if(userDto.getRoleId()==1){ %>
								
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administration</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.SUBJECT_CTL%>">Add Subject</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.ATTENDANCE_CTL%>">Add Attendance</a></li>
											<li class="nav-item"><a class="nav-link" href="<%=SIMView.ASSIGNMENT_CTL%>">Add Assignment</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.USER_CTL%>">Add User</a></li>
											<li class="nav-item"><a class="nav-link" href="<%=SIMView.FEE_CTL%>">Add Fee</a></li>
									</ul>
								</li> 
								
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Details</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.STUDENT_LIST_CTL%>">Student Detail</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.ATTENDANCE_LIST_CTL%>">Attendance Detail</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.ASSIGNMENT_LIST_CTL%>">Assignment Detail</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.SUBJECT_LIST_CTL%>">Subject Detail</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.FEE_LIST_CTL%>">Fee Detail</a></li>
									</ul>
								</li> 
								
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">My Account</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.MY_PROFILE_CTL%>">My Profile</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
										
										
									</ul>
								</li> 
												
				<%}
				if(userDto.getRoleId()==2){%>
				
		
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Details</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.SUBJECT_LIST_CTL%>">Subject Detail</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.ATTENDANCE_LIST_CTL%>">My Attendance</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.ASSIGNMENT_LIST_CTL%>">My Assignments</a></li>
											<li class="nav-item"><a class="nav-link" href="<%=SIMView.FEE_LIST_CTL%>">Fee Detail</a></li>
										
									
									
									</ul>
								</li>
								
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">My Account</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.MY_PROFILE_CTL%>">My Profile</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
										
										
									</ul>
								</li> 
						
					<%} 
					if(userDto.getRoleId()==3){ %>
								
								
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administration</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.SUBJECT_CTL%>">Add Subject</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.ATTENDANCE_CTL%>">Add Attendance</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.ASSIGNMENT_CTL%>">Add Assignment</a></li>
									
									</ul>
								</li> 
								
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Details</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.ATTENDANCE_LIST_CTL%>">Attendance Detail</a></li>
											<li class="nav-item"><a class="nav-link" href="<%=SIMView.ASSIGNMENT_LIST_CTL%>">Assignment Detail</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.SUBJECT_LIST_CTL%>">Subject Detail</a></li>
			
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.STUDENT_LIST_CTL%>">Student Detail</a></li>
										
									</ul>
								</li> 
								
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">My Account</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.MY_PROFILE_CTL%>">My Profile</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=SIMView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
										
										
									</ul>
								</li> 
								
												
				<%}	}%>
					
 				<%	if (userLoggedIn) {
						 %>
				 
							
								<li class="nav-item"><a class="nav-link" href="<%=SIMView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a></li>
								
					<%}else{ %>			
								<li class="nav-item"><a class="nav-link" href="<%=SIMView.LOGIN_CTL%>">Login</a></li> 
								
					<%} %>
							</ul>
						</div> 
					</div>
            	</nav>
            </div>
        </header>
</body>
</html>