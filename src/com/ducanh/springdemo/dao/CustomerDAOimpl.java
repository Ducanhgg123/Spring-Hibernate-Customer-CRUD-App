package com.ducanh.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ducanh.springdemo.entity.Customer;
import com.ducanh.springdemo.utility.SortUtils;

@Repository
public class CustomerDAOimpl implements CustomerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers(int sortState) {
		
		//Get the current hibernate session
		Session session=sessionFactory.getCurrentSession();
		
		//create query
		Query<Customer> query;
		if (sortState==SortUtils.FIRST_NAME)
			query=session.createQuery("from Customer order by firstName",
					Customer.class);
		else if (sortState==SortUtils.LAST_NAME)
			query=session.createQuery("from Customer order by lastName",
					Customer.class);
		else query=session.createQuery("from Customer order by email",
				Customer.class);
		
		// Retrieve list from executing query
		List<Customer> customers=query.getResultList();
		
		//return the result
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session=sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {
		Session session=sessionFactory.getCurrentSession();
		return session.get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id) {
		Session session=sessionFactory.getCurrentSession();
		Customer customer=session.get(Customer.class, id);
		session.delete(customer);
		
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		Session session=sessionFactory.getCurrentSession();
		Query<Customer> query;
		//if searchName !=null
		if (searchName!=null && searchName.trim().length()>0) {
			query=session.createQuery("from Customer where lower(firstName) like :searchName or lower(lastName) like :searchName");
			query.setParameter("searchName", "%"+searchName.toLowerCase()+"%");
		}
		// get all customers
		else {
			query=session.createQuery("from Customer");
		}
		List<Customer> customers=query.getResultList();
		return customers;
	}

}
