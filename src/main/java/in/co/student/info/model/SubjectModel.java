package in.co.student.info.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.student.info.bean.SubjectBean;
import in.co.student.info.exception.ApplicationException;
import in.co.student.info.exception.DatabaseException;
import in.co.student.info.exception.DuplicateRecordException;
import in.co.student.info.util.JDBCDataSource;


/**
 * JDBC Implementation of Subject Model
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 */
public class SubjectModel {
	
	
	/**
	 * Find next PK of Subject
	 * 
	 * @throws DatabaseException
	 */

	private static Logger log=Logger.getLogger(SubjectModel.class);
	public Integer nextPK() throws DatabaseException {
		log.debug("SubjectModel nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT MAX(ID) FROM S_SUBJECT");
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
		 log.debug("SubjectModel nextPK End");
		return pk + 1;
	}

	/**
	 * Add a Subject
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */
	
	public long add(SubjectBean bean) throws ApplicationException,
			DuplicateRecordException {
		 log.debug("SubjectModel add Started");
		Connection conn = null;
		int pk = 0;
		
		SubjectBean duplicataRole = findByName(bean.getName());

		// Check if create Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("SUBJECT already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO S_SUBJECT VALUES(?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in add SUBJECT");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("SubjectModel add End");
		return pk;
	}

	/**
	 * Delete a Subject
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * @throws applicationException 
	 */
	public void delete(SubjectBean bean) throws ApplicationException {
		 log.debug("SubjectModel delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM S_SUBJECT WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in delete Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("SubjectModel delete Started");
	}

	/**
	 * Find Subject by Name
	 * 
	 * @param name
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 * * @throws applicationException
	 */
	public SubjectBean findByName(String name) throws ApplicationException {
		log.debug("SubjectModel findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_SUBJECT WHERE SUBJECTNAME=?");
		SubjectBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
			pstmt.setString(1, name);
		
			ResultSet rs=pstmt.executeQuery();
	
			while (rs.next()) {
		
				bean = new SubjectBean();
			
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
			}
			rs.close();
		} catch (Exception e) {
			log.error("DataBase Exaption"+e);
			throw new ApplicationException(
					"Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("SubjectModel findByName end");
		return bean;
	}
	
	
	/**
	 * Find Subject by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
* @throws applicationException
	 */
	public SubjectBean findByPK(long pk) throws ApplicationException {
		 log.debug("SubjectModel findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM S_SUBJECT WHERE ID=?");
		SubjectBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
			}
			rs.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("SubjectModel findByPK End");
		return bean;
	}

	/**
	 * Update a Subject
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * @throws applicationException
	 */
	public void update(SubjectBean bean) throws ApplicationException,
			DuplicateRecordException {
		
		 log.debug("SubjectModel update Started");
		 
	
		Connection conn = null;
		SubjectBean duplicataSubject = findByName(bean.getName());

		
		// Check if updated Role already exist
		if (duplicataSubject != null
				&& duplicataSubject.getId() != bean.getId()) {
			throw new DuplicateRecordException("Subject already exists");
		}
		
		try {
			
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE s_subject SET SUBJECTNAME=?,description=?,createdBy=?,modifiedBy=?,createdDatetime=?,modifiedDatetime=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
		
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDatetime());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7, bean.getId());

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("SubjectModel update End");
	}

	/**
	 * Search Subject
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(SubjectBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Subject with pagination
	 * 
	 * @return list : List of Subject
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */
	public List search(SubjectBean bean, int pageNo, int pageSize)
			throws ApplicationException {
		 log.debug("SubjectModel search Started");
		 
		 
		 
		StringBuffer sql = new StringBuffer("SELECT * FROM s_subject WHERE 1=1");
		if (bean != null) {
			System.out.println("inside query append");
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND SUBJECTNAME LIKE '" + bean.getName() + "%'");
			}
			if (bean.getDescription() != null
					&& bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription()
						+ "%'");
			}
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			System.out.println("inside pagelimit ");
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
			System.out.println("inside pagelimit end");
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			System.out.println("before getconnection");
			conn = JDBCDataSource.getConnection();
			System.out.println("before statement");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			System.out.println("before whuile loop of rs ");
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				list.add(bean);
			}
			System.out.println("After While loop ");
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("SubjectModel search End");
		System.out.println("end of method "+list);
		return list;
	}

	/**
	 * Get List of Subject
	 * 
	 * @return list : List of Subject
	 * @throws DatabaseException
	 * @throws applicationException
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Subject with pagination
	 * 
	 * @return list : List of Subject
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		 log.debug("SubjectModel list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from S_SUBJECT");
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
				SubjectBean bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting list of Subbject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("SubjectModel list End");
		return list;

	}

	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		SubjectModel s = new SubjectModel();
		SubjectBean bean = new SubjectBean();
		/*bean.setName("admin");
		List l = s.search(bean);
		SubjectBean bean1 = (SubjectBean) l.get(0);
		System.out.println(bean1.getValue());
		System.out.println(bean1.getName());
		System.out.println(bean1.getModifiedBy());*/
		/*bean=s.findByPK(8);
		System.out.println(bean.getName());*/
		/*bean.setId(4);
		bean.setName("osaaaaaaa");
		bean.setDescription("jadaaisaviav");
		s.update(bean);*/
	bean=s.findByName("OS");
	System.out.println(bean.getDescription());
	
	}

}
