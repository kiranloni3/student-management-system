package in.co.student.info.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.student.info.bean.AssignmentBean;
import in.co.student.info.bean.BaseBean;
import in.co.student.info.bean.FeeBean;
import in.co.student.info.bean.UserBean;
import in.co.student.info.exception.ApplicationException;
import in.co.student.info.exception.DuplicateRecordException;
import in.co.student.info.model.AssignmentModel;
import in.co.student.info.model.FeeModel;
import in.co.student.info.model.SubjectModel;
import in.co.student.info.model.UserModel;
import in.co.student.info.util.DataUtility;
import in.co.student.info.util.DataValidator;
import in.co.student.info.util.PropertyReader;
import in.co.student.info.util.ServletUtility;

/**
 * Servlet implementation class FeeCtl
 */
@WebServlet(name = "FeeCtl", urlPatterns = { "/ctl/FeeCtl" })
public class FeeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(FeeCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */

	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("FeeCtl preload method start");
		 UserModel  model= new UserModel();
		try {
			UserBean bean=new UserBean();
			bean.setRoleId(2L);
			List l = model.search(bean);
			request.setAttribute("studentList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("FeeCtl preload method end");
	}
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("FeeCtl validate method start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getValue("error.require", "Amount"));
			pass = false;
		} 


		if ("-----Select-----".equalsIgnoreCase(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		}
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("month"))) {
			request.setAttribute("month", PropertyReader.getValue("error.require", "Month"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "Date"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("totalfee"))) {
			request.setAttribute("totalfee", PropertyReader.getValue("error.require", "Total Fee"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("paidFee"))) {
			request.setAttribute("paidFee", PropertyReader.getValue("error.require", "Paid Fee"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("balanceFee"))) {
			request.setAttribute("balanceFee", PropertyReader.getValue("error.require", "Balance Fee"));
			pass = false;
		}

		

		log.debug("FeeCtl validate method end");
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("FeeCtl populateBean method start");
		FeeBean bean = new FeeBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setMonth(DataUtility.getString(request.getParameter("month")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("name")));
		bean.setDate(DataUtility.getDate(request.getParameter("date")));
		
		if (!"".equalsIgnoreCase(request.getParameter("amount"))
				&& !DataValidator.isName(request.getParameter("amount"))
				&& (DataValidator.isInteger(request.getParameter("amount")))) {
		bean.setAmount(DataUtility.getLong(request.getParameter("amount")));
		}
		

		bean.setTotalfee(DataUtility.getLong(request.getParameter("totalfee")));
	
		

		bean.setPaidFee(DataUtility.getLong(request.getParameter("paidFee")));
	
		

		bean.setBalanceFee(DataUtility.getLong(request.getParameter("balanceFee")));
		

		populateDTO(bean, request);
		// TODO Auto-generated method stub
		log.debug("FeeCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FeeCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));

		FeeModel model = new FeeModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		ServletUtility.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			FeeBean bean;
			try {
				bean = model.findByPk(id);
				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("FeeCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FeeCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		FeeBean bean = (FeeBean) populateBean(request);
		FeeModel model = new FeeModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setBean(bean, request);
				} else {
					long pk = model.add(bean);
					ServletUtility.setSuccessMessage("Data is Successfully Saved", request);
					ServletUtility.forward(getView(), request, response);
				}
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Assignment is Already exist", request);
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SIMView.FEE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SIMView.FEE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("FeeCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SIMView.FEE_VIEW;
	}

}
