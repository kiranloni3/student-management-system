<%@page import="in.co.student.info.controller.AttendanceCtl"%>
<%@page import="in.co.student.info.controller.AssignmentCtl"%>
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
<title>Attendance</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div
		style="width: 100%; background-color: #04091e; height: 62px; padding: 19px; margin-top: 68px">
		<h3 style="color: white; font-family: inherit;">Attendance</h3>
	</div>
	<br>
	<div class="container">
		<h1 class="well">Add Attendance</h1>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<hr>

		<div class="col-lg-12 well">
			<div class="row">
				<form action="<%=SIMView.ATTENDANCE_CTL%>" method="post">


					<%
						List l = (List) request.getAttribute("subjectList");
						List l2 = (List) request.getAttribute("studentList");
					%>
					<jsp:useBean id="bean" class="in.co.student.info.bean.AttendanceBean"
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
							<label>Select Subject</label>
							<%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), l)%>
							<font color="red"><%=ServletUtility.getErrorMessage("subjectId", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Select Student</label>
								<%=HTMLUtility.getList("studentId", String.valueOf(bean.getStudentId()), l2)%>
							<font color="red"><%=ServletUtility.getErrorMessage("studentId", request)%></font> 
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Date</label> <input type="text" placeholder="Enter  Date Here.." name="date" class="form-control" value="<%=DataUtility.getDateString(bean.getDate()) %>">
								<font  color="red"> <%=ServletUtility.getErrorMessage("date", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Lecture Title</label> <input type="text" placeholder="Enter Lecture Title Here.." name="title" class="form-control" value="<%=DataUtility.getStringData(bean.getLectureTitle())%>">
								<font  color="red"> <%=ServletUtility.getErrorMessage("title", request)%></font>
							</div>
						</div>
						<div class="form-group">
							<label>Status</label>
							<%
							HashMap map = new HashMap();
							map.put("Present ", "Present");
							map.put("Absent", "Absent");
							
							

							String htmlList = HTMLUtility.getList("description", bean.getDescription(), map);
						%> <%=htmlList%>
								<font  color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
						</div>
						
						<input type="submit" name="operation" value="<%=AttendanceCtl.OP_SAVE%>"
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