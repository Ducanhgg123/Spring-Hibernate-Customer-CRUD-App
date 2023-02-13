package com.ducanh.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducanh.springdemo.dao.CustomerDAO;
import com.ducanh.springdemo.entity.Customer;
import com.ducanh.springdemo.service.CustomerService;
import com.ducanh.springdemo.utility.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomer(@RequestParam(required = false,name = "sort")String sortState,Model model) {
		//If no sortState than sort last Name
		if (sortState==null)
			sortState=Integer.toString(SortUtils.LAST_NAME);
		List<Customer> customers=customerService.getCustomers(Integer.parseInt(sortState));
		model.addAttribute("customers",customers);
		return "list-customer";
	}
	
	@GetMapping("/addCustomer")
	public String showAddCustomerForm(Model model) {
		model.addAttribute("customer",new Customer());
		return "add-customer";
	}
	
	@PostMapping("saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/updateCustomer")
	public String showUpdateCustomerForm(@RequestParam("customerId") int id,Model model) {
		Customer customer=customerService.getCustomer(id);
		model.addAttribute("customer",customer);
		return "add-customer";
	}
	
	@GetMapping("/deleteCustomer")
	public String showDeleteCustomerForm(@RequestParam("customerId") int id,Model model) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/searchCustomer")
	public String searchCustomer(@RequestParam("searchName") String searchName,Model model) {
		model.addAttribute("customers",customerService.searchCustomers(searchName));
		return "list-customer";
	}
}
