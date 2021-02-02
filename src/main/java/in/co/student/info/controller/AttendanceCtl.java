package in.co.student.info.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.student.info.bean.AttendanceBean;
import in.co.student.info.bean.BaseBean;
import in.co.student.info.bean.UserBean;
import in.co.student.info.exception.ApplicationException;
import in.co.student.info.exception.DuplicateRecordException;
import in.co.student.info.model.AttendanceModel;
import in.co.student.info.model.SubjectModel;
import in.co.student.info.model.UserModel;
import in.co.student.info.util.DataUtility;
import in.co.student.info.util.DataValidator;
import in.co.student.info.util.PropertyReader;
import in.co.student.info.util.ServletUtility;

/**
 * Servlet implementation class AttendanceCtl
 */
@WebServlet(name = "AttendanceCtl", urlPatterns = { "/ctl/AttendanceCtl" })
public class AttendanceCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(AttendanceCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */

	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("AttendanceCtl preload method start");
		 SubjectModel  model= new SubjectModel();
		 UserModel uModel=new UserModel();
		try {
			UserBean uBean=new UserBean();
			List l = model.list();
			uBean.setRoleId(2L);
			List l2=uModel.search(uBean);
			request.setAttribute("subjectList", l);
			request.setAttribute("studentList", l2);
		} catch (ApplicationException e) {
			log.error(e);
		}
		log.debug("AttendanceCtl preload method end");
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("AttendanceCtl validate method start");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("title"))) {
			request.setAttribute("title", PropertyReader.getValue("error.require", "Title"));
			pass = false;
		} 
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Status"));
			pass = false;
		}
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("studentId"))) {
			request.setAttribute("studentId", PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "Date"));
			pass = false;
		}

		

		log.debug("AttendanceCtl validate method end");
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
		log.debug("AttendanceCtl populateBean method start");
		AttendanceBean bean = new AttendanceBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setLectureTitle(DataUtility.getString(request.getParameter("title")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setDate(DataUtility.getDate(request.getParameter("date")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));

		populateDTO(bean, request);
		// TODO Auto-generated method stub
		log.debug("AttendanceCtl populateBean method end");
		return bean;
	}

	/**
	 * Contains display logic
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("AttendanceCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));

		AttendanceModel model = new AttendanceModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		ServletUtility.setOpration("Add", request);
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			AttendanceBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setOpration("Edit", request);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("AttendanceCtl doGet method end");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("AttendanceCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		AttendanceBean bean = (AttendanceBean) populateBean(request);
		AttendanceModel model = new AttendanceModel();
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
				ServletUtility.setErrorMessage("Attendance is Already exist", request);
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SIMView.ATTENDANCE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SIMView.ATTENDANCE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("AttendanceCtl doPost method end");
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SIMView.ATTENDANCE_VIEW;
	}

}
