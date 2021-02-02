package in.co.student.info.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.student.info.bean.BaseBean;
import in.co.student.info.bean.SubjectBean;
import in.co.student.info.exception.ApplicationException;
import in.co.student.info.exception.DuplicateRecordException;
import in.co.student.info.model.SubjectModel;
import in.co.student.info.util.DataUtility;
import in.co.student.info.util.DataValidator;
import in.co.student.info.util.PropertyReader;
import in.co.student.info.util.ServletUtility;



/**
 * Servlet implementation class SubjectCtl
 */

/**
 * Subject functionality Controller. Performs operation for add, update,
 * delete and get Subject
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */
@WebServlet(name="SubjectCtl",urlPatterns={"/ctl/SubjectCtl"})

public class SubjectCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log=Logger.getLogger(SubjectCtl.class);
	
	
	
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
    protected boolean validate(HttpServletRequest request) {

		log.debug("SubjectCtl validate method start");
      
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("name"))) {
            request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name",PropertyReader.getValue("error.name", "Name"));
			pass = false;
		}

        if (DataValidator.isNull(request.getParameter("description"))) {
            request.setAttribute("description",
                    PropertyReader.getValue("error.require", "Description"));
            pass = false;
        }
        
        if ("-----Select-----".equalsIgnoreCase(request.getParameter("subjectId"))) {
			request.setAttribute("subjectId",
					PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}
        
        

        log.debug("SubjectCtl validate method end");
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
		log.debug("SubjectCtl populateBean method start");
		SubjectBean bean=new SubjectBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		
		populateDTO(bean, request);
		// TODO Auto-generated method stub
		log.debug("SubjectCtl populateBean method end");
	return bean;
	}
	/**
	 * Contains display logic
	 */
	@Override
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("SubjectCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       SubjectModel model = new SubjectModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            SubjectBean bean;
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
	        log.debug("SubjectCtl doGet method end");
    }
	/**
	 * Contains submit logic
	 */
	@Override
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("SubjectCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		SubjectBean bean=(SubjectBean)populateBean(request);
		SubjectModel model=new SubjectModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			try {
				if(id>0){
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setBean(bean, request);
				}else {
				long pk=model.add(bean);
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
				ServletUtility.setErrorMessage("Subject is Already exist", request);
				e.printStackTrace();
			}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
				ServletUtility.redirect(SIMView.SUBJECT_LIST_CTL, request, response);
				return;
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SIMView.SUBJECT_CTL, request, response);
			return;
	}
		ServletUtility.forward(getView(), request, response);
		log.debug("SubjectCtl doPost method end");
	}
	
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return SIMView.SUBJECT_VIEW;
	}}
