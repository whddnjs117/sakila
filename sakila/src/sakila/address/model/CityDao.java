package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class CityDao {
	
	public List<City> selectCityByCountry(int countryId) {
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql ="SELECT city_id ,city FROM city where country_id=? ORDER BY city";
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, countryId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				city.setCityId(rs.getInt("city_id"));
				city.setCity(rs.getString("city"));
				list.add(city);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs,stmt,conn);
		}
		return list;
	}
	public void insertCity(City city) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql ="INSERT INTO city(city,country_id,last_update) VALUES(?,?,now())";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,city.getCity());
			stmt.setInt(2, city.getCountry().getCountryId());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(null,stmt,conn);
		}
	}
	
	public int selectCityCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM city";
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
	public List<City> selectCityList(int currentPage){
		/*
		 * city INNER JOIN country
		 */
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		final int RowPerPage = 10;
		int beginRow = (currentPage-1)*RowPerPage;
		String sql ="SELECT ci.city_id,ci.city,ci.country_id,c.country,ci.last_update from city ci INNER JOIN country c ON ci.country_id = c.country_id ORDER BY city_id DESC LIMIT ?,?";
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,beginRow);
			stmt.setInt(2,RowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				city.setCityId(rs.getInt("ci.city_id"));
				city.setCity(rs.getString("ci.city"));
				city.setCountry(new Country());
				city.getCountry().setCountryId(rs.getInt("ci.country_id"));
				city.getCountry().setCountry(rs.getString("c.country"));
				city.setLastUpdate(rs.getString("ci.last_update"));
				list.add(city);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs,stmt,conn);
		}
		return list;
	}
}
