<%@page import="in.co.student.info.controller.SubjectCtl"%>
<%@page import="in.co.student.info.util.ServletUtility"%>
<%@page import="in.co.student.info.controller.SIMView"%>
<%@page import="in.co.student.info.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subject</title>
</head>
<body>
<%@include file="Header.jsp"%>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 25px ;margin-top: 68px" >
<h4 style="color: white; font-family: inherit;">SUBJECT</h4></div>
<br>
<div class="container">
    <h1 class="well">Add Subject</h1>
     <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
      </font></b>
    <hr>
    <br>
	<div class="col-lg-12 well">
	<div class="row">
				<form action="<%=SIMView.SUBJECT_CTL%>" method="post">
				
				<jsp:useBean id="bean" class="in.co.student.info.bean.SubjectBean"
            scope="request"></jsp:useBean>
            
        
              
               <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            
					<div class="col-lg-12">
									
						<div class="form-group">
							<label>Subject Name</label> <input type="text" name="name"
									placeholder="Enter Subject Name Here..." class="form-control"
									value="<%=DataUtility.getStringData(bean.getName())%>">
								&nbsp;<font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font>
						</div>
						
						<div class="form-group">
							<label>Description</label>
							<textarea placeholder="Enter Description Here.." rows="2" name="description" 
								class="form-control"><%=DataUtility.getStringData(bean.getDescription())%></textarea>
						</div>
						
					<input type="submit"
                        name="operation" class="btn btn-lg btn-info" value="<%=SubjectCtl.OP_SAVE %>" >					
					</div>
					
				</form> 
				</div>
	</div>
	</div>
	<br><br>
	<hr>
	
<%@include file="Footer.jsp"%>
</body>
</html>