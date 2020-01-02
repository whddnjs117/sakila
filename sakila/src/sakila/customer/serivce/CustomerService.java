package sakila.customer.serivce;

import java.sql.*;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.db.DBHelper;

public class CustomerService {
	private AddressDao addressDao;
	private CustomerDao customerDao;
	public void insertCustomer(Address address,Customer customer) {
		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			conn.setAutoCommit(false);
			addressDao = new AddressDao();
			int addressId = addressDao.insertAddress(conn,address);
			System.out.println("CustomerService addressId : "+addressId);
			customer.getAddress().setAddressId(addressId);
			
			customerDao = new CustomerDao();
			customerDao.insertCustomer(conn,customer);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			DBHelper.close(null, null, conn);
		}
	}
}
