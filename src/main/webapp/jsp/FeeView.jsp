<%@page import="in.co.student.info.controller.FeeCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.student.info.util.HTMLUtility"%>
<%@page import="in.co.student.info.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.student.info.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fee</title>
</head>
<body>
<%@include file="Header.jsp"%>
	<div
		style="width: 100%; background-color: #04091e; height: 62px; padding: 19px; margin-top: 68px">
		<h3 style="color: white; font-family: inherit;">Fee</h3>
	</div>
	<br>
	<div class="container">
		<h1 class="well">Add Fee</h1>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<hr>

		<div class="col-lg-12 well">
			<div class="row">
				<form action="<%=SIMView.FEE_CTL%>" method="post">


					<%
						List l = (List) request.getAttribute("studentList");
					%>
					<jsp:useBean id="bean" class="in.co.student.info.bean.FeeBean"
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
							<label>Select Student</label>
							<%=HTMLUtility.getList("name", String.valueOf(bean.getStudentId()), l)%>
							<font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Month</label> <%
							HashMap map = new HashMap();
							map.put("January ", "January");
							map.put("February", "February");
							map.put("March ", "March");
							map.put("April ", "April");
							map.put("May", "May");
							map.put("June", "June");
							map.put("July", "July");
							map.put("August", "August");
							map.put("September", "September");
							map.put("October", "October");
							map.put("November", "November");
							map.put("December", "December");
							

							String htmlList = HTMLUtility.getList("month", bean.getMonth(), map);
						%> <%=htmlList%>
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("month", request)%></font>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-sm-6 form-group">
							<label>Amount</label>
							<input type="text" placeholder="Enter Amount Here.." name="amount" class="form-control" value="<%=DataUtility.getStringData(bean.getAmount())%>">
								<font  color="red"> <%=ServletUtility.getErrorMessage("amount", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Total Fee</label> <input type="text" name="totalfee"
									placeholder="Enter Total Fee" class="form-control"
									value="<%=DataUtility.getStringData(bean.getTotalfee())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("totalfee", request)%></font>
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-6 form-group">
							<label>Paid Fee</label>
							<input type="text" placeholder="Enter Paid Fee Here.." name="paidFee" class="form-control" value="<%=DataUtility.getStringData(bean.getPaidFee())%>">
								<font  color="red"> <%=ServletUtility.getErrorMessage("paidFee", request)%></font>
							</div>
							<div class="col-sm-6 form-group">
								<label>Balance Fee</label> <input type="text" name="balanceFee"
									placeholder="Enter Total Fee" class="form-control"
									value="<%=DataUtility.getStringData(bean.getBalanceFee())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("balanceFee", request)%></font>
							</div>
						</div>
						
						<div class="form-group">
							<label>Date</label>
							<input type="text" placeholder="Enter Date Here.." name="date" class="form-control" value="<%=DataUtility.getDateString(bean.getDate())%>">
								<font  color="red"> <%=ServletUtility.getErrorMessage("date", request)%></font>
						</div>
					
						
						<input type="submit" name="operation" value="<%=FeeCtl.OP_SAVE%>"
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