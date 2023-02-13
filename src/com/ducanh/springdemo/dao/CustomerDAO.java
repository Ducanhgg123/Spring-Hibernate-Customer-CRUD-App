package com.ducanh.springdemo.dao;

import java.util.List;

import com.ducanh.springdemo.entity.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomers(int sortState);
	public void saveCustomer(Customer customer);
	public Customer getCustomer(int id);
	public void deleteCustomer(int id);
	public List<Customer> searchCustomers(String searchName);
}
