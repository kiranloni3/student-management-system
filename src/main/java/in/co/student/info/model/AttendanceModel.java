package in.co.student.info.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.student.info.bean.AttendanceBean;
import in.co.student.info.bean.SubjectBean;
import in.co.student.info.bean.UserBean;
import in.co.student.info.exception.ApplicationException;
import in.co.student.info.exception.DatabaseException;
import in.co.student.info.exception.DuplicateRecordException;
import in.co.student.info.util.JDBCDataSource;

public class AttendanceModel {

	private static Logger log = Logger.getLogger(AttendanceModel.class);

	/**
	 * Find next PK of Student
	 * 
	 * @throws DatabaseException
	 *
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM S_Attendance");
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
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public void delete(AttendanceBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM S_Attendance WHERE ID=?");
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
			throw new ApplicationException("Exception : Exception in delete attendance");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	public AttendanceBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Attendance WHERE ID=?");
		AttendanceBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AttendanceBean();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
				bean.setStudentId(rs.getLong(4));
				bean.setStudentName(rs.getString(5));
				bean.setLectureTitle(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setDescription(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Attendance by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public AttendanceBean findBySubNameDateTitle(long subjectId, long studentId, Date date, String title)
			throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM S_Attendance WHERE subjectId=? and studentId=? and date=? and lectureTitle=?");
		AttendanceBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, subjectId);
			pstmt.setLong(2, studentId);
			pstmt.setDate(3, new java.sql.Date(date.getTime()));
			pstmt.setString(4, title);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new AttendanceBean();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
				bean.setStudentId(rs.getLong(4));
				bean.setStudentName(rs.getString(5));
				bean.setLectureTitle(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setDescription(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Attendance by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public long add(AttendanceBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		// get College Name

		AttendanceBean duplicateName = findBySubNameDateTitle(bean.getSubjectId(), bean.getStudentId(), bean.getDate(),
				bean.getLectureTitle());
		int pk = 0;

		if (duplicateName != null) {
			throw new DuplicateRecordException("Attendance already exists");
		}

		UserModel uModel = new UserModel();
		UserBean ubean = uModel.findByPK(bean.getStudentId());
		bean.setStudentName(ubean.getFirstName() + " " + ubean.getLastName());

		SubjectModel sModel = new SubjectModel();
		SubjectBean sbean = sModel.findByPK(bean.getSubjectId());
		bean.setSubjectName(sbean.getName());

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO S_Attendance VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getSubjectId());
			pstmt.setString(3, bean.getSubjectName());
			pstmt.setLong(4, bean.getStudentId());
			pstmt.setString(5, bean.getStudentName());
			pstmt.setString(6, bean.getLectureTitle());
			pstmt.setDate(7, new java.sql.Date(bean.getDate().getTime()));
			pstmt.setString(8, bean.getDescription());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
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

	public void update(AttendanceBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		AttendanceBean beanExist =findBySubNameDateTitle(bean.getSubjectId(), bean.getStudentId(), bean.getDate(),
				bean.getLectureTitle());

		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Email Id is already exist");
		}
		
		UserModel uModel = new UserModel();
		UserBean ubean = uModel.findByPK(bean.getStudentId());
		bean.setStudentName(ubean.getFirstName() + " " + ubean.getLastName());

		SubjectModel sModel = new SubjectModel();
		SubjectBean sbean = sModel.findByPK(bean.getSubjectId());
		bean.setSubjectName(sbean.getName());

	
		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE S_Attendance SET subjectId=?,subjectName=?,StudentId=?,studentName=?,lectureTitle=?,DATE=?,description=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getSubjectId());
			pstmt.setString(2, bean.getSubjectName());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, bean.getStudentName());
			pstmt.setString(5, bean.getLectureTitle());
			pstmt.setDate(6, new java.sql.Date(bean.getDate().getTime()));
			pstmt.setString(7, bean.getDescription());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Attendance ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	public List search(AttendanceBean bean) throws ApplicationException {
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

    public List search(AttendanceBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM S_Attendance WHERE 1=1");

        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getSubjectName()!= null && bean.getSubjectName().length() > 0) {
                sql.append(" AND subjectName like '" + bean.getSubjectName()
                        + "%'");
            }
            if (bean.getStudentName() != null && bean.getStudentName().length() > 0) {
                sql.append(" AND StudentName like '" + bean.getStudentName() + "%'");
            }
            if (bean.getDate() != null && bean.getDate().getDate() > 0) {
                sql.append(" AND date = " + bean.getDate());
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
                bean = new AttendanceBean();
                bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
				bean.setStudentId(rs.getLong(4));
				bean.setStudentName(rs.getString(5));
				bean.setLectureTitle(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setDescription(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
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
        StringBuffer sql = new StringBuffer("select * from S_Attendance");
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
                AttendanceBean bean = new AttendanceBean();
                bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getLong(2));
				bean.setSubjectName(rs.getString(3));
				bean.setStudentId(rs.getLong(4));
				bean.setStudentName(rs.getString(5));
				bean.setLectureTitle(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setDescription(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Attendance");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;

    }

}
