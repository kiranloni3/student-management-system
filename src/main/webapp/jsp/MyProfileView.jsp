<%@page import="in.co.student.info.controller.MyProfileCtl"%>
<%@page import="in.co.student.info.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.student.info.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.student.info.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
</head>
<body>
<%@include file="Header.jsp"%>
	<div
		style="width: 100%; background-color: #04091e; height: 62px; padding: 19px; margin-top: 68px">
		<h3 style="color: white; font-family: inherit;">MY PROFILE</h3>
	</div>
	<br>
	<div class="container">
		<h1 class="well">My Profile</h1>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<hr>

		<div class="col-lg-12 well">
			<div class="row">
				<form action="<%=SIMView.MY_PROFILE_CTL%>" method="post">


					
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

							String htmlList = HTMLUtility.getList("gender",bean.getGender(), map);
						%> <%=htmlList%>&nbsp; <font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Date Of Birth</label> <input type="text" name="dob"
									placeholder="Enter Date Of Birth" class="form-control"
									value="<%=DataUtility.getDateString(bean.getDob())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font>
							</div>
						</div>
						

						<input type="submit" name="operation" value="<%=MyProfileCtl.OP_SAVE%>"
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