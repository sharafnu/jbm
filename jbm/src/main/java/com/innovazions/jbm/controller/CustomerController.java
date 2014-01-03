package com.innovazions.jbm.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovazions.jbm.entity.Customer;
import com.innovazions.jbm.service.CustomerService;
import com.innovazions.jbm.view.CustomerView;

/**
 * Handles requests for the customer related actions.
 */
@Controller
public class CustomerController {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/customerInfoAdd", method = RequestMethod.GET)
	public String customerInfoAdd(Locale locale, Model model) {
		logger.info("CustomerController > customerInfoAdd");
		return "customerInfoAdd";
	}

	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	public String saveCustomer(
			@ModelAttribute("customerView") CustomerView customerView,
			BindingResult result, Model model) {
		logger.info("CustomerController > saveCustomer");
		System.out.println("Customer Name:" + customerView.getFirstName()
				+ " Mobile:" + customerView.getMobile1());
		Customer customer = customerView.convertViewToEntity();
		customer.setLastModifiedUser("demo");
		customer.setLastModifiedDate(new Date());
		Long customerId = customerService.createCustomer(customer);
		System.out.println("Customer Id : " + customerId);
		model.addAttribute("customerId", customerId);
		return "customerInfoEdit";
	}

	@RequestMapping(value = "/customerListJSON", method = RequestMethod.GET)
	public @ResponseBody
	List<CustomerView> customerListJSON() {
		logger.info("CustomerController > customerListJSON");
		List<Customer> customerList = customerService.getCustomerList(null);
		return new Customer().convertEntitiesToViews(customerList);
	}

	@RequestMapping(value = "/getCustomerDetailsJSON/{customerId}", method = RequestMethod.GET)
	public @ResponseBody
	CustomerView getCustomerDetailsJSON(@PathVariable Long customerId) {
		logger.info("CustomerController > getCustomerDetails :" + customerId);
		Customer customer = customerService
				.getCustomerDetailsByCustomerId(customerId);
		return customer.convertEntityToView();
	}

	@RequestMapping(value = "/getCustomerAddressListJSON/{customerId}", method = RequestMethod.GET)
	public @ResponseBody
	List<CustomerView> getCustomerAddressListJSON(@PathVariable Long customerId) {
		logger.info("CustomerController > getCustomerAddressListJSON");
		List<Customer> customerList = customerService.getCustomerList(null);
		return new Customer().convertEntitiesToViews(customerList);
	}

	@RequestMapping(value = "/customerInfoEdit", method = RequestMethod.GET)
	public String customerInfoEdit(Locale locale, Model model) {
		logger.info("CustomerController > customerInfoAdd");
		return "customerInfoEdit";
	}

}
