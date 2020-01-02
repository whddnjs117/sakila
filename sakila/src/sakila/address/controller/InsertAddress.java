package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.address.model.City;



@WebServlet("/address/InsertAddress")
public class InsertAddress extends HttpServlet {
	private AddressDao addressDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("appliction/json;charset=UTF-8");
		
		addressDao = new AddressDao();
		Address add = new Address();
		System.out.println(":::::InsertAddress 서블렛 :::::");
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		String address = request.getParameter("address");
		String address2 = request.getParameter("address2");
		String district = request.getParameter("district");
		String postalCode = request.getParameter("postalCode");
		String phone = request.getParameter("phone");
		System.out.println(cityId);
		System.out.println(address);
		System.out.println(address2);
		System.out.println(district);
		System.out.println(postalCode);
		System.out.println(phone);
		
		add.setAddress(address);
		add.setAddress2(address2);
		add.setCity(new City());
		add.getCity().setCityId(cityId);
		add.setDistrict(district);
		add.setPostalCode(postalCode);
		add.setPhone(phone);
		
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(cityId);
		
		response.getWriter().write(jsonStr);
		
	}

}
