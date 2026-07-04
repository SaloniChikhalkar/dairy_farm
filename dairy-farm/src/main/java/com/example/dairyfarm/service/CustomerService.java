package com.example.dairyfarm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dairyfarm.entity.Customer;
import com.example.dairyfarm.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Add Customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get All Customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get Customer By Id
    public Customer getCustomerById(Long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        return optional.orElse(null);
    }

    // Update Customer
    public Customer updateCustomer(Long id, Customer customer) {

        Customer existingCustomer = getCustomerById(id);

        if (existingCustomer != null) {

            existingCustomer.setCustomerName(customer.getCustomerName());
            existingCustomer.setMobileNumber(customer.getMobileNumber());
            existingCustomer.setAddress(customer.getAddress());

            return customerRepository.save(existingCustomer);
        }

        return null;
    }

    // Delete Customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
