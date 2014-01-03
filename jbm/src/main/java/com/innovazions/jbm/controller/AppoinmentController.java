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
import com.innovazions.jbm.entity.CustomerAddress;
import com.innovazions.jbm.entity.CustomerContract;
import com.innovazions.jbm.service.CustomerAddressService;
import com.innovazions.jbm.service.CustomerContractService;
import com.innovazions.jbm.service.CustomerService;
import com.innovazions.jbm.view.CustomerAddressView;
import com.innovazions.jbm.view.CustomerContractView;
import com.innovazions.jbm.view.CustomerView;

/**
 * Handles requests for the customer related actions.
 */
@Controller
public class AppoinmentController {

	private static final Logger logger = LoggerFactory
			.getLogger(AppoinmentController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerAddressService customerAddressService;

	@Autowired
	private CustomerContractService customerContractService;

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
	List<CustomerAddressView> getCustomerAddressListJSON(
			@PathVariable Long customerId) {
		logger.info("CustomerController > getCustomerAddressListJSON");
		List<CustomerAddress> customerAddressList = customerAddressService
				.getCustomerAddressListByCustomerId(customerId);
		return new CustomerAddress()
				.convertEntitiesToViews(customerAddressList);
	}

	@RequestMapping(value = "/customerInfoEdit", method = RequestMethod.GET)
	public String customerInfoEdit(Locale locale, Model model) {
		logger.info("CustomerController > customerInfoAdd");
		return "customerInfoEdit";
	}

	@RequestMapping(value = "/saveCustomerAddress", method = RequestMethod.POST)
	public @ResponseBody
	String saveCustomerAddress(
			@ModelAttribute("customerAddressView") CustomerAddressView customerAddressView,
			BindingResult result) {
		System.out.println("Customer Address:"
				+ customerAddressView.getAddressType() + " Area Id:"
				+ customerAddressView.getAreaId());
		customerAddressView.setLastModifiedDate(new Date());
		customerAddressView.setLastModifiedUser("SYSTEM");
		CustomerAddress customerAddress = customerAddressView
				.convertViewToEntity();
		customerAddressService.createCustomerAddress(customerAddress);
		return "Success";
	}

	/*
	 * Customer Contract actions starts
	 */

	@RequestMapping(value = "/customerContractDetails", method = RequestMethod.GET)
	public String customerContractDetails() {
		logger.info("CustomerController > customerContractDetails");
		return "customerContractDetails";
	}

	@RequestMapping(value = "/getCustomerContractListJSON/{customerId}", method = RequestMethod.GET)
	public @ResponseBody
	List<CustomerContractView> getCustomerContractListJSON(
			@PathVariable Long customerId) {
		logger.info("CustomerController > getCustomerContractListJSON");
		List<CustomerContract> customerContractList = customerContractService
				.geCustomerContractListByCustomerId(customerId);
		return new CustomerContract()
				.convertEntitiesToViews(customerContractList);
	}

	@RequestMapping(value = "/saveCustomerContract", method = RequestMethod.POST)
	public @ResponseBody
	String saveCustomerContract(
			@ModelAttribute("customerAddressView") CustomerContractView customerContractView,
			BindingResult result) {
		System.out.println("Customer Contract:"
				+ customerContractView.getCustomerId() + " Area Id:"
				+ customerContractView.getContractNo());
		customerContractView.setLastModifiedDate(new Date());
		customerContractView.setLastModifiedUser("SYSTEM");
		CustomerContract customerContract = customerContractView
				.convertViewToEntity();
		customerContractService.createCustomerContract(customerContract);
		return "Success";
	}
}
