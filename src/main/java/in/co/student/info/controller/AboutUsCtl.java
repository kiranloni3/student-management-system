package in.co.student.info.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

import in.co.student.info.util.ServletUtility;




 
@WebServlet(name = "AboutUsCtl", urlPatterns = { "/AboutUsCtl" })
public class AboutUsCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;

	
	public AboutUsCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ServletUtility.forward(SIMView.ABOUT_VIEW, request, response);
	
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SIMView.ABOUT_VIEW;
	}

}
