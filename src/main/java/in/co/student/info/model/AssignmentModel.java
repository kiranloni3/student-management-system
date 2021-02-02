package in.co.student.info.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import in.co.student.info.bean.AssignmentBean;
import in.co.student.info.bean.SubjectBean;
import in.co.student.info.exception.ApplicationException;
import in.co.student.info.exception.DatabaseException;
import in.co.student.info.exception.DuplicateRecordException;
import in.co.student.info.util.JDBCDataSource;

public class AssignmentModel {

	private static Logger log = Logger.getLogger(AssignmentModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("AssignmentModel nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM S_Assignment");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("AssignmentModel nextPK End");
		return pk + 1;
	}

	public AssignmentBean findByName(String name) throws ApplicationException {
		log.debug("AssignmentModel findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Assignment WHERE AssignmentTitle=?");
		AssignmentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AssignmentBean();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
				bean.setAssignmentTitle(rs.getString(4));
				bean.setDueDate(rs.getDate(5));
				bean.setAssignmentFile(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			
			log.error("DataBase Exaption" + e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("AssignmentModel findByName end");
		return bean;
	}
	
	
	
	public AssignmentBean findByPk(long pk) throws ApplicationException {
		log.debug("AssignmentModel findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Assignment WHERE id=?");
		AssignmentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AssignmentBean();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
				bean.setAssignmentTitle(rs.getString(4));
				bean.setDueDate(rs.getDate(5));
				bean.setAssignmentFile(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("DataBase Exaption" + e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("AssignmentModel findByName end");
		return bean;
	}

	public AssignmentBean findByNameDate(String name, Date date) throws ApplicationException {
		log.debug("AssignmentModel findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Assignment WHERE AssignmentTitle=? and DueDate=?");
		AssignmentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.setDate(2, new java.sql.Date(date.getTime()));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AssignmentBean();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
				bean.setAssignmentTitle(rs.getString(4));
				bean.setDueDate(rs.getDate(5));
				bean.setAssignmentFile(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("DataBase Exaption" + e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("AssignmentModel findByName end");
		return bean;
	}

	public long add(AssignmentBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		// get College Name

		SubjectModel model = new SubjectModel();
		SubjectBean sBean = model.findByPK(bean.getSubjectId());
		bean.setSubjectName(sBean.getName());

		AssignmentBean duplicateName = findByNameDate(bean.getAssignmentTitle(), bean.getDueDate());
		int pk = 0;

		if (duplicateName != null) {
			throw new DuplicateRecordException("Assigment already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO S_Assignment VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getSubjectId());
			pstmt.setString(3, bean.getSubjectName());
			pstmt.setString(4, bean.getAssignmentTitle());
			pstmt.setDate(5, new java.sql.Date(bean.getDueDate().getTime()));
			pstmt.setString(6, bean.getAssignmentFile());
			pstmt.setString(7, bean.getDescription());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	public void delete(AssignmentBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM S_Assignment WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Assignment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	public void update(AssignmentBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		AssignmentBean beanExist = findByNameDate(bean.getAssignmentTitle(),bean.getDueDate());

		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Assignment is already exist");
		}

		// get College Name
		SubjectModel sModel = new SubjectModel();
		SubjectBean sBean = sModel.findByPK(bean.getSubjectId());
		bean.setSubjectName(sBean.getName());

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE s_assignment SET SUBJECTID=?,SubjectNAME=?,AssignmentTitle=?,DueDate=?,Assignmentfile=?,description=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getSubjectId());
			pstmt.setString(2, bean.getSubjectName());
			pstmt.setString(3, bean.getAssignmentTitle());
			pstmt.setDate(4, new java.sql.Date(bean.getDueDate().getTime()));
			pstmt.setString(5, bean.getAssignmentFile());
			pstmt.setString(6, bean.getDescription());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Student ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	/**
     * Search Student
     * 
     * @param bean
     *            : Search Parameters
     * @throws DatabaseException
     *  @throws ApplicationException
     */

    public List search(AssignmentBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

    /**
     * Search Student with pagination
     * 
     * @return list : List of Students
     * @param bean
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * 
     * @throws DatabaseException
     *  @throws ApplicationException
     */

    public List search(AssignmentBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM S_Assignment WHERE 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getAssignmentTitle() != null && bean.getAssignmentTitle().length() > 0) {
                sql.append(" AND AssignmentTitle like '" + bean.getAssignmentTitle()
                        + "%'");
            }
            
            if (bean.getDueDate() != null && bean.getDueDate().getDate() > 0) {
                sql.append(" AND DueDate = " + bean.getDueDate());
            }
           

        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }

        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new AssignmentBean();
                bean.setId(rs.getLong(1));
                bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
				bean.setAssignmentTitle(rs.getString(4));
				bean.setDueDate(rs.getDate(5));
				bean.setAssignmentFile(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model search End");
        return list;
    }
    
    
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of Student with pagination
     * 
     * @return list : List of Student
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     *  @throws ApplicationException
     */

    public List list(int pageNo, int pageSize) throws ApplicationException {
      System.out.println("in list method ");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from S_Assignment");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AssignmentBean bean = new AssignmentBean();
                bean.setId(rs.getLong(1));
                bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
				bean.setAssignmentTitle(rs.getString(4));
				bean.setDueDate(rs.getDate(5));
				bean.setAssignmentFile(rs.getString(6));
				bean.setDescription(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;
    }


}
