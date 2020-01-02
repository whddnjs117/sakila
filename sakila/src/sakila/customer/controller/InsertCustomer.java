package sakila.customer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.address.model.City;
import sakila.business.model.Store;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.customer.serivce.CustomerService;



@WebServlet("/insertCustomer")
public class InsertCustomer extends HttpServlet {

	private CustomerService customerService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//address request.getParam
		Address add= new Address();
		System.out.println(":::::InsertAddress 서블렛 :::::");
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		String address = request.getParameter("address");
		String address2 = request.getParameter("address2");
		String district = request.getParameter("district");
		String postalCode = request.getParameter("postalCode");
		String phone = request.getParameter("phone");

		System.out.println("::::::Address 객체:::::::");
		System.out.println(cityId);
		System.out.println(address);
		System.out.println(address2);
		System.out.println(district);
		System.out.println(postalCode);
		System.out.println(phone);
//		
		add.setAddress(address);
		add.setAddress2(address2);
		add.setCity(new City());
		add.getCity().setCityId(cityId);
		add.setDistrict(district);
		add.setPostalCode(postalCode);
		add.setPhone(phone);

		//customer request.getParam
		Customer customer = new Customer();
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		System.out.println(":::::: Customer 객체::::::");
		System.out.println(storeId);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		
		customer.setStore(new Store());
		customer.getStore().setStoreId(storeId);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		customer.setAddress(new Address());
		
		customerService = new CustomerService();
		customerService.insertCustomer(add, customer);
	}

}
