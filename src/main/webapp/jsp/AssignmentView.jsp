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
<title>Assignment</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div
		style="width: 100%; background-color: #04091e; height: 62px; padding: 19px; margin-top: 68px">
		<h3 style="color: white; font-family: inherit;">Assignment</h3>
	</div>
	<br>
	<div class="container">
		<h1 class="well">Add Assignment</h1>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<hr>

		<div class="col-lg-12 well">
			<div class="row">
				<form action="<%=SIMView.ASSIGNMENT_CTL%>" method="post">


					<%
						List l = (List) request.getAttribute("subjectList");
					%>
					<jsp:useBean id="bean" class="in.co.student.info.bean.AssignmentBean"
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
								<label>Assignment Title</label> <input type="text" name="title"
									placeholder="Enter Assignment Title Here..." class="form-control"
									value="<%=DataUtility.getStringData(bean.getAssignmentTitle())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("title", request)%></font>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Due Date</label> <input type="text" placeholder="Enter Due Date Here.." name="dueDate" class="form-control" value="<%=DataUtility.getDateString(bean.getDueDate()) %>">
								<font  color="red"> <%=ServletUtility.getErrorMessage("dueDate", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Assignment File</label> <input type="text" placeholder="Enter Assignment File Here.." name="aFile" class="form-control" value="<%=DataUtility.getStringData(bean.getAssignmentFile())%>">
								<font  color="red"> <%=ServletUtility.getErrorMessage("aFile", request)%></font>
							</div>
						</div>
						<div class="form-group">
							<label>Description</label>
							<textarea placeholder="Enter Description Here.." rows="2" name="description" 
								class="form-control"><%=DataUtility.getStringData(bean.getDescription())%></textarea>
						</div>
						
						<input type="submit" name="operation" value="<%=AssignmentCtl.OP_SAVE%>"
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