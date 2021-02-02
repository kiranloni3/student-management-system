package in.co.student.info.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.student.info.bean.AssignmentBean;
import in.co.student.info.bean.FeeBean;
import in.co.student.info.bean.SubjectBean;
import in.co.student.info.bean.UserBean;
import in.co.student.info.exception.ApplicationException;
import in.co.student.info.exception.DatabaseException;
import in.co.student.info.exception.DuplicateRecordException;
import in.co.student.info.util.JDBCDataSource;

public class FeeModel {

	private static Logger log = Logger.getLogger(FeeModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("FeeModel nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM S_Fee");
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
		log.debug("FeeModel nextPK End");
		return pk + 1;
	}
	
	public FeeBean findByName(String name) throws ApplicationException {
		log.debug("FeeModel findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Fee WHERE name=?");
		FeeBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FeeBean();
				bean.setId(rs.getLong(1));
				bean.setStudentId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setMonth(rs.getString(4));
				bean.setAmount(rs.getLong(5));
				bean.setDate(rs.getDate(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setTotalfee(rs.getLong(11));
				bean.setPaidFee(rs.getLong(12));
				bean.setBalanceFee(rs.getLong(13));
			}
			rs.close();
		} catch (Exception e) {
			
			log.error("DataBase Exaption" + e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("FeeModel findByName end");
		return bean;
	}
	
	public FeeBean findByNameMonth(long id, String month) throws ApplicationException {
		log.debug("FeeModel findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_Fee WHERE studentId=? and month=?");
		FeeBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			pstmt.setString(2, month);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FeeBean();
				bean.setId(rs.getLong(1));
				bean.setStudentId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setMonth(rs.getString(4));
				bean.setAmount(rs.getLong(5));
				bean.setDate(rs.getDate(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setTotalfee(rs.getLong(11));
				bean.setPaidFee(rs.getLong(12));
				bean.setBalanceFee(rs.getLong(13));
			}
			rs.close();
		} catch (Exception e) {
			
			log.error("DataBase Exaption" + e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("FeeModel findByName end");
		return bean;
	}
	
	public FeeBean findByPk(long pk) throws ApplicationException {
		log.debug("FeeModel findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM S_fee WHERE id=?");
		FeeBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FeeBean();
				bean.setId(rs.getLong(1));
				bean.setStudentId(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setMonth(rs.getString(4));
				bean.setAmount(rs.getLong(5));
				bean.setDate(rs.getDate(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setTotalfee(rs.getLong(11));
				bean.setPaidFee(rs.getLong(12));
				bean.setBalanceFee(rs.getLong(13));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("DataBase Exaption" + e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("FeeModel findByName end");
		return bean;
	}
	
	public long add(FeeBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		// get College Name

		UserModel sModel = new UserModel();
        UserBean studentbean = sModel.findByPK(bean.getStudentId());
        bean.setName(studentbean.getFirstName() + " " + studentbean.getLastName());

        FeeBean duplicateName = findByNameMonth(bean.getStudentId(), bean.getMonth());
		int pk = 0;

		if (duplicateName != null) {
			throw new DuplicateRecordException("Fee already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO S_Fee VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getMonth());
			pstmt.setLong(5,bean.getAmount());
			pstmt.setDate(6, new java.sql.Date(bean.getDate().getTime()));
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11,bean.getTotalfee());
			pstmt.setLong(12,bean.getPaidFee());
			pstmt.setLong(13,bean.getBalanceFee());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public void delete(FeeBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM S_Fee WHERE ID=?");
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
	
	public void update(FeeBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		FeeBean duplicateName = findByNameMonth(bean.getStudentId(), bean.getMonth());

		// Check if updated Roll no already exist
		if (duplicateName != null && duplicateName.getId() != bean.getId()) {
			throw new DuplicateRecordException("Fee is already exist");
		}

		UserModel sModel = new UserModel();
        UserBean studentbean = sModel.findByPK(bean.getStudentId());
        bean.setName(studentbean.getFirstName() + " " + studentbean.getLastName());

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE s_fee SET StudentID=?,NAME=?,month=?,amount=?,date=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,totalfee=?,paidfee=?,balancefee=? WHERE ID=?");
			pstmt.setLong(1, bean.getStudentId());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getMonth());
			pstmt.setLong(4,bean.getAmount());
			pstmt.setDate(5, new java.sql.Date(bean.getDate().getTime()));
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10,bean.getTotalfee());
			pstmt.setLong(11,bean.getPaidFee());
			pstmt.setLong(12,bean.getBalanceFee());
			pstmt.setLong(13, bean.getId());
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
	
	
	 public List search(FeeBean bean) throws ApplicationException {
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

	    public List search(FeeBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM S_Fee WHERE 1=1");

	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            if (bean.getName() != null && bean.getName().length() > 0) {
	                sql.append(" AND name like '" + bean.getName()
	                        + "%'");
	            }
	            if (bean.getMonth() != null && bean.getMonth().length() > 0) {
	                sql.append(" AND month like '" + bean.getMonth()
	                        + "%'");
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
	                bean = new FeeBean();
	                bean.setId(rs.getLong(1));
					bean.setStudentId(rs.getLong(2));
					bean.setName(rs.getString(3));
					bean.setMonth(rs.getString(4));
					bean.setAmount(rs.getLong(5));
					bean.setDate(rs.getDate(6));
					bean.setCreatedBy(rs.getString(7));
					bean.setModifiedBy(rs.getString(8));
					bean.setCreatedDatetime(rs.getTimestamp(9));
					bean.setModifiedDatetime(rs.getTimestamp(10));
					bean.setTotalfee(rs.getLong(11));
					bean.setPaidFee(rs.getLong(12));
					bean.setBalanceFee(rs.getLong(13));
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
	        StringBuffer sql = new StringBuffer("select * from S_Fee");
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
	                FeeBean bean = new FeeBean();
	                bean.setId(rs.getLong(1));
					bean.setStudentId(rs.getLong(2));
					bean.setName(rs.getString(3));
					bean.setMonth(rs.getString(4));
					bean.setAmount(rs.getLong(5));
					bean.setDate(rs.getDate(6));
					bean.setCreatedBy(rs.getString(7));
					bean.setModifiedBy(rs.getString(8));
					bean.setCreatedDatetime(rs.getTimestamp(9));
					bean.setModifiedDatetime(rs.getTimestamp(10));
					bean.setTotalfee(rs.getLong(11));
					bean.setPaidFee(rs.getLong(12));
					bean.setBalanceFee(rs.getLong(13));
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
