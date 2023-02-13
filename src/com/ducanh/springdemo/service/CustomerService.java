package com.ducanh.springdemo.service;

import java.util.List;

import com.ducanh.springdemo.entity.Customer;

public interface CustomerService {
	public List<Customer> getCustomers(int sortState);
	public void saveCustomer(Customer customer);
	public Customer getCustomer(int id);
	public void deleteCustomer(int id);
	public List<Customer> searchCustomers(String searchName);
}
