<%@page import="in.co.student.info.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.student.info.bean.AttendanceBean"%>
<%@page import="in.co.student.info.model.AttendanceModel"%>
<%@page import="in.co.student.info.util.ServletUtility"%>
<%@page import="in.co.student.info.controller.AttendanceListCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attendance List</title>
<script src="/StudentInformationSystem/js/jquery.min.js"></script>
<script language="javascript">
	$(function() {
		$("#selectall").click(function() {
			$('.case').attr('checked', this.checked);
		});
		$(".case").click(function() {

			if ($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});
</script>
</head>
<body>
<%@ include file="Header.jsp" %>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 19px ;margin-top: 68px" >
<h2 style="color: white; font-family:inherit;">Attendance List</h2></div>
<br><br>
<form action="<%=SIMView.ATTENDANCE_LIST_CTL%>" method="post">
<div class="container">
	<div class="col-lg-12 well">
	<div class="row">
					<div class="col-sm-12">
					<div class="row">
							<div class="col-sm-3 form-group">
								<input type="text" placeholder="Enter Student Name Here.." name="name" class="form-control" >
							</div>
							
							
							<div class="col-sm-3 form-group">
								
								<input type="submit"
                       			 name="operation" class="btn btn-md btn-info" value="<%=AttendanceListCtl.OP_SEARCH%>" >
							</div>
						</div>					
			</div>		
		</div>
	</div>
</div>
<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
									</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
									</font></b>
<table class="table">
			<thead class="thead-dark">
				<tr>
				<% UserBean uBean=(UserBean)session.getAttribute("user");%>
				<%if(uBean.getRoleId()==1 
					|| uBean.getRoleId()==3){ %>
					<th><input type="checkbox" id="selectall">Select All</th>
					<%} %>
					<th scope="col">S.No.</th>
					<th scope="col">Subject Name</th>
					<th scope="col">Student Name</th>
					<th scope="col">Lecture Name</th>
					<th scope="col">Date</th>
					<th scope="col">Status</th>
					
					<%if(uBean.getRoleId()==1 
					|| uBean.getRoleId()==3){ %>
					<th scope="col">Edit</th>
				<%} %>
				</tr>
			</thead>
			 <%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				AttendanceBean bean = null;
				List list = ServletUtility.getList(request);
				Iterator<AttendanceBean> i = list.iterator();
				while (i.hasNext()) {
					bean = i.next();
				%> 
			<tbody>
				<tr>
				<%if(uBean.getRoleId()==1 
					|| uBean.getRoleId()==3){ %>
					<td align="center"><input type="checkbox" class="case"
						name="ids" value="<%=bean.getId()%>"></td>
					<%} %>
					<th scope="row"><%=index++%></th>
					<td><%=bean.getSubjectName()%></td>
					<td><%=bean.getStudentName()%></td>
					<td><%=bean.getLectureTitle()%></td>
					<td><%=DataUtility.getDateString(bean.getDate())%>
					<td><%=bean.getDescription()%></td>
					<% if(uBean.getRoleId()==1 
							|| uBean.getRoleId()==3) {%>
					<td align="center">
						
						<a class="btn btn-success" href="AttendanceCtl?id=<%=bean.getId()%>">Edit</a> 
					</td>
					<%} %>
					</tr>
					<%} %>
				
				
			</tbody>
		</table>
		
		<table width="99%">
				<tr>
					<td><input type="submit" name="operation" class="btn btn-success"
						value="<%=AttendanceListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
						
						<%if(uBean.getRoleId()==1 
					|| uBean.getRoleId()==3){ %>
						<td><input type="submit" name="operation" class="btn btn-danger"
						value="<%=AttendanceListCtl.OP_DELETE%>"
						<%=(list.size() == 0) ? "disabled" : ""%>></td>
					<%} %>
				
					<%
						AttendanceModel model = new AttendanceModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-success"
						value="<%=AttendanceListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>></td>

				</tr>
			</table>
		
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
<br><br>
	<%@ include file="Footer.jsp"%>
</body>
</html>