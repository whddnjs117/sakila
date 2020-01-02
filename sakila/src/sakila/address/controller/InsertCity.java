package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.*;


@WebServlet("/address/InsertCity")
public class InsertCity extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		String city = request.getParameter("city");
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		System.out.println(city);
		System.out.println(countryId);
		
		cityDao = new CityDao();
		City city1 = new City();
		city1.setCountry(new Country());
		city1.setCity(city);
		city1.getCountry().setCountryId(countryId);
		
		cityDao.insertCity(city1);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(city);
		
		response.getWriter().write(jsonStr);
	}

}
