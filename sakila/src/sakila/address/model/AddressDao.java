package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class AddressDao {
	public List<Address> selectAddressList(int currentPage){
		List<Address> list = new ArrayList<Address>();
		Connection conn = null;
		PreparedStatement stmt = null;
		final int RowPerPage = 10;
		int beginRow = (currentPage-1)*RowPerPage;
		String sql ="SELECT a.address_id,a.address,a.address2,a.district,a.city_id,ci.city ,a.phone,a.postal_code,a.last_update,co.country_id,co.country FROM address a INNER JOIN city ci ON a.city_id=ci.city_id INNER JOIN country co ON ci.country_id=co.country_id ORDER BY a.address_id DESC LIMIT ?,?";
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,beginRow);
			stmt.setInt(2,RowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Address address = new Address();
				address.setAddressId(rs.getInt("a.address_id"));
				address.setAddress(rs.getString("a.address"));
				address.setAddress2(rs.getString("a.address2"));
				address.setDistrict(rs.getString("a.district"));
				address.setCity(new City());
				address.getCity().setCity(rs.getString("ci.city"));
				address.getCity().setCityId(rs.getInt("a.city_id"));
				address.setPostalCode(rs.getString("a.postal_code"));
				address.setPhone(rs.getString("a.phone"));
				address.getCity().setCountry(new Country());
				address.getCity().getCountry().setCountryId(rs.getInt("co.country_id"));
				address.getCity().getCountry().setCountry(rs.getString("co.country"));
				address.setLastUpdate(rs.getString("a.last_update"));
				list.add(address);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs,stmt,conn);
		}
		return list;
	}
	public int insertAddress(Connection conn,Address address) throws Exception {
		int addressId = 0;
		//Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="INSERT INTO address(address,address2,district,city_id,postal_code,phone,last_update) VALUES(?,?,?,?,?,?,now())";
			//conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, address.getAddress());
			stmt.setString(2,address.getAddress2());
			stmt.setString(3, address.getDistrict());
			stmt.setInt(4,address.getCity().getCityId());
			stmt.setString(5, address.getPostalCode());
			stmt.setString(6, address.getPhone());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys(); //primary 키값을 받을수있다.
			if(rs.next()) {
				addressId = rs.getInt(1);
			}
			stmt.close();
			return addressId;
	}
	public int selectAddressCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM Address";
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
