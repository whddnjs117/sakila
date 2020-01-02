package sakila.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sakila.address.model.Address;
import sakila.db.DBHelper;

public class CustomerDao {
	public void insertCustomer(Connection conn,Customer customer) throws Exception{
		//Connection conn = null;
		PreparedStatement stmt = null;
		String sql ="INSERT INTO customer(store_id,first_name,last_name,email,address_id,active,create_date,last_update) VALUES(?,?,?,?,?,1,now(),now())";
		
			//conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer.getStore().getStoreId());
			stmt.setString(2,customer.getFirstName());
			stmt.setString(3, customer.getLastName());
			stmt.setString(4,customer.getEmail());
			stmt.setInt(5, customer.getAddress().getAddressId());
			stmt.executeUpdate();
			stmt.close();
		
	}
	public int selectCustomerCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM customer";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs,stmt,conn);
		}
		return count;
	}
}
