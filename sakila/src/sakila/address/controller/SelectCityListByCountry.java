package sakila.address.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.City;
import sakila.address.model.CityDao;


@WebServlet("/selectCityListByCountry")
public class SelectCityListByCountry extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		System.out.println(":::::selectCityListByCountry 서블렛:::::");
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		System.out.println(countryId);
		cityDao = new CityDao();
		List<City> list = cityDao.selectCityByCountry(countryId);
		
		Gson gson = new Gson();
		
		String jsonStr = gson.toJson(list);
		
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
	}

}
