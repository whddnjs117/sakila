package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.AddressDao;
import sakila.address.model.Country;
import sakila.address.model.CountryDao;


@WebServlet("/address/InsertCountry")
public class InsertCountry extends HttpServlet {

	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		String country = request.getParameter("country");
		System.out.println(country);
		
		countryDao = new CountryDao();
		Country c = new Country();
		c.setCountry(country);
		countryDao.insertCountry(c);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(country);
		
		response.getWriter().write(jsonStr);
	}

}
