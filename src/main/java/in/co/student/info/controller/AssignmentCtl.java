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
import in.co.student.info.exception.ApplicationException;
import in.co.student.info.exception.DuplicateRecordException;
import in.co.student.info.model.AssignmentModel;
import in.co.student.info.model.RoleModel;
import in.co.student.info.model.SubjectModel;
import in.co.student.info.util.DataUtility;
import in.co.student.info.util.DataValidator;
import in.co.student.info.util.PropertyReader;
import in.co.student.info.util.ServletUtility;

/**
 * Servlet implementation class AssignmentCtl
 */

@WebServlet(name = "AssignmentCtl", urlPatterns = { "/ctl/AssignmentCtl" })
public class AssignmentCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(AssignmentCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */

	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("AssignmentCtl preload method start");
		 SubjectModel  model= new SubjectModel();
		try {
			List l = model.list();
			request.setAttribute("subjectList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("AssignmentCtl preload method end");
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("AssignmentCtl validate method start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("title"))) {
			request.setAttribute("title", PropertyReader.getValue("error.require", "Title"));
			pass = false;
		} 

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dueDate"))) {
			request.setAttribute("dueDate", PropertyReader.getValue("error.require", "Due Date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("aFile"))) {
			request.setAttribute("aFile", PropertyReader.getValue("error.require", "Assignment File"));
			pass = false;
		}

		log.debug("AssignmentCtl validate method end");
		return pass;
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("AssignmentCtl populateBean method start");
		AssignmentBean bean = new AssignmentBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setAssignmentFile(DataUtility.getString(request.getParameter("aFile")));
		bean.setAssignmentTitle(DataUtility.getString(request.getParameter("title")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setDueDate(DataUtility.getDate(request.getParameter("dueDate")));

		populateDTO(bean, request);
		// TODO Auto-generated method stub
		log.debug("AssignmentCtl populateBean method end");
		return bean;
	}

	/**
	 * Contains display logic
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("AssignmentCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));

		AssignmentModel model = new AssignmentModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		ServletUtility.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			AssignmentBean bean;
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
		log.debug("AssignmentCtl doGet method end");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("AssignmentCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		AssignmentBean bean = (AssignmentBean) populateBean(request);
		AssignmentModel model = new AssignmentModel();
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
			ServletUtility.redirect(SIMView.ASSIGNMENT_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SIMView.ASSIGNMENT_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("AssignmentCtl doPost method end");
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SIMView.ASSIGNMENT_VIEW;
	}
}
