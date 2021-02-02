<%@page import="in.co.student.info.controller.UserCtl"%>
<%@page import="javax.xml.crypto.Data"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="in.co.student.info.util.HTMLUtility"%>
<%@page import="in.co.student.info.util.DataUtility"%>
<%@page import="in.co.student.info.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div
		style="width: 100%; background-color: #04091e; height: 62px; padding: 19px; margin-top: 68px">
		<h3 style="color: white; font-family: inherit;">User</h3>
	</div>
	<br>
	<div class="container">
		<h1 class="well">Add User</h1>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<hr>

		<div class="col-lg-12 well">
			<div class="row">
				<form action="<%=SIMView.USER_CTL%>" method="post">


					<%
						List l = (List) request.getAttribute("roleList");
					%>
					<jsp:useBean id="bean" class="in.co.student.info.bean.UserBean"
						scope="request"></jsp:useBean>

					<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedBy()%>"> <input type="hidden"
						name="createdDatetime"
						value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
					<input type="hidden" name="modifiedDatetime"
						value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

					<div class="col-sm-12">
					
					
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Select User Role</label>
							<%=HTMLUtility.getList("roleId", String.valueOf(bean.getRoleId()), l)%>
							<font color="red"><%=ServletUtility.getErrorMessage("roleId", request)%></font>
							</div>
							
						</div>

						

						<div class="row">
							<div class="col-sm-6 form-group">
								<label>First Name</label> <input type="text" name="firstName"
									placeholder="Enter First Name" class="form-control"
									value="<%=DataUtility.getStringData(bean.getFirstName())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Last Name</label> <input type="text" name="lastName"
									placeholder="Enter Last Name" class="form-control"
									value="<%=DataUtility.getStringData(bean.getLastName())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
							</div>
						</div>
						
						<div class="form-group">
							<label>Login Id</label> <input type="text" placeholder="Enter Login Id Here.." name="login" class="form-control" value="<%=DataUtility.getStringData(bean.getLogin())%>">
								<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Password</label> <input type="password" placeholder="Enter Password Here.." name="password" class="form-control" value="<%=DataUtility.getStringData(bean.getPassword()) %>">
								<font  color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Confirm Password</label> <input type="password" placeholder="Enter Confirm Password Here.." name="confirmPassword" class="form-control" value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>">
								<font  color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>School</label> <input type="text" name="school"
									placeholder="Enter School Name" class="form-control"
									value="<%=DataUtility.getStringData(bean.getSchool())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("school", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Course</label> <input type="text" name="course"
									placeholder="Enter Course Name" class="form-control"
									value="<%=DataUtility.getStringData(bean.getCourse())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("course", request)%></font>
							</div>
						</div>
						
						<div class="form-group">
							<label>Phone Number</label> <input type="text" name="mobileNo"
									placeholder="Enter Phone Number" class="form-control"
									value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font>
						</div>

						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Gender</label> 
								<%
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");

							String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
						%> <%=htmlList%>&nbsp; <font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Date Of Birth (DD/MM/YYYY) </label> <input type="text" name="dob"
									placeholder="Enter Date Of Birth" class="form-control"
									value="<%=DataUtility.getDateString(bean.getDob())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font>
							</div>
						</div>
						<div class="form-group">
							<label>Email Address</label> 
							<input type="text" name="emailId"
									placeholder="Enter Email Address" class="form-control"
									value="<%=DataUtility.getStringData(bean.getEmailId())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("emailId", request)%></font>
						</div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<label>Country</label><input type="text" name="country"
									placeholder="Enter Country Here... " class="form-control"
									value="<%=DataUtility.getStringData(bean.getCountry())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("country", request)%></font>
							</div>
							<div class="col-sm-4 form-group">
								<label>State</label> <input type="text" name="state"
									placeholder="Enter State Here... " class="form-control"
									value="<%=DataUtility.getStringData(bean.getState())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font>
							</div>
							<div class="col-sm-4 form-group">
								<label>City</label> <input type="text" name="city"
									placeholder="Enter City Here... " class="form-control"
									value="<%=DataUtility.getStringData(bean.getCity())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font>
							</div>
						</div>
						<div class="form-group">
							<label>Address Line 1</label>
							<textarea placeholder="Enter Address Line 1 Here.." rows="2" name="address1" 
								class="form-control"><%=DataUtility.getStringData(bean.getAddress1())%></textarea>
						</div>
						<div class="form-group">
							<label>Address Line 2</label>
							<textarea placeholder="Enter Address Here.." rows="2" name="address2"
								class="form-control"><%=DataUtility.getStringData(bean.getAddress2()) %></textarea>
						</div>

						<input type="submit" name="operation" value="<%=UserCtl.OP_SAVE%>"
							class="btn btn-lg btn-info" >
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
	<br>
	<hr>
	<br>
	<br>
	<%@include file="Footer.jsp"%>
</body>
</html>